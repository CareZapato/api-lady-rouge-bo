package com.ladyrouge.LadyRouge.services.apiServices.DTO;

import java.util.List;

import lombok.Data;

@Data
public class InsertarTallasResponse {
    String response;
    String error;
    List<String> listaTallas;
    int nuevos;
    int repetidos;
    Long eliminados;
}