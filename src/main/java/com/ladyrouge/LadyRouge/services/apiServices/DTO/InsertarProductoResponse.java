package com.ladyrouge.LadyRouge.services.apiServices.DTO;

import com.ladyrouge.LadyRouge.models.Producto;

import lombok.Data;

@Data
public class InsertarProductoResponse {
    String response;
    String error;
    Producto producto;
    Long eliminados;
}