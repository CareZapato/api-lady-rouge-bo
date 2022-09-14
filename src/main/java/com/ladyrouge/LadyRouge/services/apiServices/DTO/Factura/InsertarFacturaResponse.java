package com.ladyrouge.LadyRouge.services.apiServices.DTO.Factura;

import lombok.Data;

@Data
public class InsertarFacturaResponse {
    String response;
    String error;
    int insertados;
    int errores;
    
}