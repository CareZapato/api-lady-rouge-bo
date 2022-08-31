package com.ladyrouge.LadyRouge.services.apiServices.DTO;

import java.util.List;

import lombok.Data;

@Data
public class InsertarColoresResponse {
    String response;
    String error;
    List<String> listaColores;
    int nuevos;
    int repetidos;
    Long eliminados;
}