package com.ladyrouge.LadyRouge.services.apiServices.DTO.Factura;

import com.ladyrouge.LadyRouge.models.Proveedor;
import com.ladyrouge.LadyRouge.models.Stock;

import lombok.Data;

@Data
public class FacturaJsonDTO {
    String fechaCompra;
    String fechaFactura;
    double costoTotal;
    double costoDespacho;
    double costoImpuesto;
    String fechaLlegadaEstimada;
    String fechaEstimada;
    Boolean estadoFactura;
    Proveedor proveedor;
    Stock stocks[];
}