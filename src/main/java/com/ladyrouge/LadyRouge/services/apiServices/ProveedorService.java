package com.ladyrouge.LadyRouge.services.apiServices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.ladyrouge.LadyRouge.models.Proveedor;
import com.ladyrouge.LadyRouge.repositories.ProveedorRepository;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.Proveedor.InsertarProveedoresResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    
    public Iterable<Proveedor> listaProveedores(){
        return proveedorRepository.findAll();
    }

    public Iterable<Proveedor> findByNombre(String proveedorEntrada){
        return proveedorRepository.findByNombre(proveedorEntrada);
    }

    public InsertarProveedoresResponse insertarProveedores(Iterable<Proveedor> proveedores){
        InsertarProveedoresResponse response = new InsertarProveedoresResponse();
        List<String> lista = new ArrayList<>();
        for(Proveedor proveedor: proveedores){
            if(Iterables.size(proveedorRepository.findByNombre(proveedor.getNombre())) == 0){
                Proveedor nuevaProveedor = new Proveedor(proveedor);
                try{
                    proveedorRepository.save(nuevaProveedor);
                    response.setNuevos(response.getNuevos() + 1);
                    lista.add(proveedor.getNombre());
                    response.setListaProveedores(lista);
                }
                catch(Exception e){
                    response.setResponse("Error al insertar proveedores");
                    response.setError(e.toString());
                    log.error("[ProveedorService][insertarProveedores] - Error al insertar proveedores");
                    return response;
                }
            }else{
                response.setRepetidos(response.getRepetidos() + 1);
            }
        }
        response.setResponse(lista.size() > 0 ? "Inserción correcta" : "No hubo inserciones");
        return response;
    }

    public InsertarProveedoresResponse eliminarProveedores(){
        InsertarProveedoresResponse response = new InsertarProveedoresResponse();
        try{
            Long cant = proveedorRepository.count();
            proveedorRepository.deleteAll();
            response.setEliminados(cant);
            response.setResponse("Se eliminaron todos las proveedores de la Base de datos");
        }catch(Exception e){
            response.setResponse("Error al eliminar proveedores");
            response.setError(e.toString());
            log.error("[ProveedorService][eliminarProveedores] - Error al eliminar proveedores");
            return response;
        }
        return response;
    }
}