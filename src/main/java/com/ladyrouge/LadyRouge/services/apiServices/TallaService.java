package com.ladyrouge.LadyRouge.services.apiServices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.ladyrouge.LadyRouge.models.Talla;
import com.ladyrouge.LadyRouge.repositories.TallaRepository;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.InsertarTallasResponse;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.TallasJsonDTO;

@Service
@Transactional
public class TallaService {

    @Autowired
    private TallaRepository tallaRepository;

    
    public Iterable<Talla> listaTallas(){
        return tallaRepository.findAll();
    }

    public Iterable<Talla> findByNombre(String tallaEntrada){
        return tallaRepository.findByNombre(tallaEntrada);
    }

    public InsertarTallasResponse insertarTallas(Iterable<TallasJsonDTO> tallasJsonDTO){
        InsertarTallasResponse response = new InsertarTallasResponse();
        List<String> lista = new ArrayList<>();
        for(TallasJsonDTO talla: tallasJsonDTO){
            if(Iterables.size(tallaRepository.findByNombre(talla.getNombre())) == 0){
                Talla nuevaTalla = new Talla(talla.getNombre(),talla.getDescripcion());
                try{
                    tallaRepository.save(nuevaTalla);
                    response.setNuevos(response.getNuevos() + 1);
                    lista.add(talla.getNombre());
                    response.setListaTallas(lista);
                }
                catch(Exception e){
                    response.setResponse("Error al insertar tallas");
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

    public InsertarTallasResponse eliminarTallas(){
        InsertarTallasResponse response = new InsertarTallasResponse();
        try{
            Long cant = tallaRepository.count();
            tallaRepository.deleteAll();
            response.setEliminados(cant);
            response.setResponse("Se eliminaron todos las tallas de la Base de datos");
        }catch(Exception e){
            response.setResponse("Error al insertar tallas");
            response.setError(e.toString());
            return response;
        }
        return response;
    }
}