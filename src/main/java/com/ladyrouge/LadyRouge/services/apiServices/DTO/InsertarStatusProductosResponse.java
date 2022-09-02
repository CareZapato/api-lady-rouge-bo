package com.ladyrouge.LadyRouge.services.apiServices.DTO;

import java.util.List;

import lombok.Data;

@Data
public class InsertarStatusProductosResponse {
    String response;
    String error;
    List<String> listaStatusProductos;
    int nuevos;
    int repetidos;
    Long eliminados;
}