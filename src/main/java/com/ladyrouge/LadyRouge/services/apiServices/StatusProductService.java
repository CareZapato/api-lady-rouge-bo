package com.ladyrouge.LadyRouge.services.apiServices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.ladyrouge.LadyRouge.models.StatusProduct;
import com.ladyrouge.LadyRouge.repositories.StatusProductRepository;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.InsertarStatusProductsResponse;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.StatusProductsJsonDTO;

@Service
@Transactional
public class StatusProductService {

    @Autowired
    private StatusProductRepository statusProductRepository;

    
    public Iterable<StatusProduct> listaStatusProducts(){
        return statusProductRepository.findAll();
    }

    public Iterable<StatusProduct> findByNombre(String statusProductEntrada){
        return statusProductRepository.findByNombre(statusProductEntrada);
    }

    public InsertarStatusProductsResponse insertarStatusProducts(Iterable<StatusProductsJsonDTO> statusProductsJsonDTO){
        InsertarStatusProductsResponse response = new InsertarStatusProductsResponse();
        List<String> lista = new ArrayList<>();
        for(StatusProductsJsonDTO statusProduct: statusProductsJsonDTO){
            if(Iterables.size(statusProductRepository.findByNombre(statusProduct.getNombre())) == 0){
                StatusProduct nuevaStatusProduct = new StatusProduct(statusProduct.getNombre(),statusProduct.getDescripcion());
                try{
                    statusProductRepository.save(nuevaStatusProduct);
                    response.setNuevos(response.getNuevos() + 1);
                    lista.add(statusProduct.getNombre());
                    response.setListaStatusProduct(lista);
                }
                catch(Exception e){
                    response.setResponse("Error al insertar statusProducts");
                    response.setError(e.toString());
                    return response;
                }
            }else{
                response.setRepetidos(response.getRepetidos() + 1);
            }
        }
        response.setResponse(lista.size() > 0 ? "Inserción correcta" : "No hubo inserciones");
        return response;
    }

    public InsertarStatusProductsResponse eliminarStatusProducts(){
        InsertarStatusProductsResponse response = new InsertarStatusProductsResponse();
        try{
            Long cant = statusProductRepository.count();
            statusProductRepository.deleteAll();
            response.setEliminados(cant);
            response.setResponse("Se eliminaron todos las statusProducts de la Base de datos");
        }catch(Exception e){
            response.setResponse("Error al insertar statusProducts");
            response.setError(e.toString());
            return response;
        }
        return response;
    }
}