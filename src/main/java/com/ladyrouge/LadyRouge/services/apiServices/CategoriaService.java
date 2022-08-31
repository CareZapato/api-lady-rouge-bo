package com.ladyrouge.LadyRouge.services.apiServices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.ladyrouge.LadyRouge.models.Categoria;
import com.ladyrouge.LadyRouge.repositories.CategoriaRepository;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.InsertarCategoriasResponse;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.CategoriasJsonDTO;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    
    public Iterable<Categoria> listaCategorias(){
        return categoriaRepository.findAll();
    }

    public Iterable<Categoria> findByNombre(String categoriaEntrada){
        return categoriaRepository.findByNombre(categoriaEntrada);
    }

    public InsertarCategoriasResponse insertarCategorias(Iterable<CategoriasJsonDTO> categoriasJsonDTO){
        InsertarCategoriasResponse response = new InsertarCategoriasResponse();
        List<String> lista = new ArrayList<>();
        for(CategoriasJsonDTO categoria: categoriasJsonDTO){
            if(Iterables.size(categoriaRepository.findByNombre(categoria.getNombre())) == 0){
                Categoria nuevaCategoria = new Categoria(categoria.getNombre(),categoria.getDescripcion());
                try{
                    categoriaRepository.save(nuevaCategoria);
                    response.setNuevos(response.getNuevos() + 1);
                    lista.add(categoria.getNombre());
                    response.setListaCategorias(lista);
                }
                catch(Exception e){
                    response.setResponse("Error al insertar categorias");
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

    public InsertarCategoriasResponse eliminarCategorias(){
        InsertarCategoriasResponse response = new InsertarCategoriasResponse();
        try{
            Long cant = categoriaRepository.count();
            categoriaRepository.deleteAll();
            response.setEliminados(cant);
            response.setResponse("Se eliminaron todos las categorias de la Base de datos");
        }catch(Exception e){
            response.setResponse("Error al insertar categorias");
            response.setError(e.toString());
            return response;
        }
        return response;
    }
}