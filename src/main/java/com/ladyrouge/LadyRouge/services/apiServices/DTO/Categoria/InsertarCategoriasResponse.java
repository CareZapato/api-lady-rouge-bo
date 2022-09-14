package com.ladyrouge.LadyRouge.services.apiServices.DTO.Categoria;

import java.util.List;

import lombok.Data;

@Data
public class InsertarCategoriasResponse {
    String response;
    String error;
    List<String> listaCategorias;
    int nuevos;
    int repetidos;
    Long eliminados;
}