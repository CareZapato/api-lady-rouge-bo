package com.ladyrouge.LadyRouge.services.apiServices.DTO.Proveedor;

import lombok.Data;
import java.util.List;

@Data
public class InsertarProveedoresResponse {
    String response;
    String error;
    List<String> listaProveedores;
    int nuevos;
    int repetidos;
    Long eliminados;
}