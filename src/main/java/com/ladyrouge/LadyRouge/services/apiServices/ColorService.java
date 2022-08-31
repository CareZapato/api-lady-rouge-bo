package com.ladyrouge.LadyRouge.services.apiServices;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.ladyrouge.LadyRouge.models.Color;
import com.ladyrouge.LadyRouge.repositories.ColorRepository;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.ColoresJsonDTO;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.InsertarColoresResponse;

@Service
@Transactional
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    
    public Iterable<Color> listaColores(){
        return colorRepository.findAll();
    }

    public Iterable<Color> findByNombre(String colorEntrada){
        return colorRepository.findByNombre(colorEntrada);
    }

    public InsertarColoresResponse insertarColores(Iterable<ColoresJsonDTO> coloresJsonDTO){
        InsertarColoresResponse response = new InsertarColoresResponse();
        List<String> lista = new ArrayList<>();
        for(ColoresJsonDTO color: coloresJsonDTO){
            if(Iterables.size(colorRepository.findByNombre(color.getNombre())) == 0){
                Color nuevoColor = new Color(color.getNombre(),color.getDescripcion());
                try{
                    colorRepository.save(nuevoColor);
                    response.setNuevos(response.getNuevos() + 1);
                    lista.add(nuevoColor.getNombre());
                    response.setListaColores(lista);
                }
                catch(Exception e){
                    response.setResponse("Error al insertar colores");
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

    public InsertarColoresResponse eliminarColores(){
        InsertarColoresResponse response = new InsertarColoresResponse();
        try{
            Long cant = colorRepository.count();
            colorRepository.deleteAll();
            response.setEliminados(cant);
            response.setResponse("Se eliminaron todos los colores de la Base de datos");
        }catch(Exception e){
            response.setResponse("Error al insertar colores");
            response.setError(e.toString());
            return response;
        }
        return response;
    }
}