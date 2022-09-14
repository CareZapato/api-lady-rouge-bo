package com.ladyrouge.LadyRouge.services.apiServices.DTO.Producto;

import lombok.Data;

@Data
public class InsertarProductosResponse {
    String response;
    String error;
    int repetidos;
    int insertados;
    int errores;
}