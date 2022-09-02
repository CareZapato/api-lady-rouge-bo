package com.ladyrouge.LadyRouge.services.apiServices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.ladyrouge.LadyRouge.models.StatusProducto;
import com.ladyrouge.LadyRouge.repositories.StatusProductoRepository;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.InsertarStatusProductosResponse;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.StatusProductosJsonDTO;

@Service
@Transactional
public class StatusProductoService {

    @Autowired
    private StatusProductoRepository statusProductoRepository;

    
    public Iterable<StatusProducto> listaStatusProductos(){
        return statusProductoRepository.findAll();
    }

    public Iterable<StatusProducto> findByNombre(String statusProductoEntrada){
        return statusProductoRepository.findByNombre(statusProductoEntrada);
    }

    public InsertarStatusProductosResponse insertarStatusProductos(Iterable<StatusProductosJsonDTO> statusProductosJsonDTO){
        InsertarStatusProductosResponse response = new InsertarStatusProductosResponse();
        List<String> lista = new ArrayList<>();
        for(StatusProductosJsonDTO statusProducto: statusProductosJsonDTO){
            if(Iterables.size(statusProductoRepository.findByNombre(statusProducto.getNombre())) == 0){
                StatusProducto nuevaStatusProducto = new StatusProducto(statusProducto.getNombre(),statusProducto.getDescripcion());
                try{
                    statusProductoRepository.save(nuevaStatusProducto);
                    response.setNuevos(response.getNuevos() + 1);
                    lista.add(statusProducto.getNombre());
                    response.setListaStatusProductos(lista);
                }
                catch(Exception e){
                    response.setResponse("Error al insertar statusProductos");
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

    public InsertarStatusProductosResponse eliminarStatusProductos(){
        InsertarStatusProductosResponse response = new InsertarStatusProductosResponse();
        try{
            Long cant = statusProductoRepository.count();
            statusProductoRepository.deleteAll();
            response.setEliminados(cant);
            response.setResponse("Se eliminaron todos las statusProductos de la Base de datos");
        }catch(Exception e){
            response.setResponse("Error al insertar statusProductos");
            response.setError(e.toString());
            return response;
        }
        return response;
    }
}