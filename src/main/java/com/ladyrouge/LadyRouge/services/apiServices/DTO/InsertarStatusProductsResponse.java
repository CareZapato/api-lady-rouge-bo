package com.ladyrouge.LadyRouge.services.apiServices.DTO;

import java.util.List;

import lombok.Data;

@Data
public class InsertarStatusProductsResponse {
    String response;
    String error;
    List<String> listaStatusProduct;
    int nuevos;
    int repetidos;
    Long eliminados;
}