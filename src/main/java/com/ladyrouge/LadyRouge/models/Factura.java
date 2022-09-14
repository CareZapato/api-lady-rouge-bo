package com.ladyrouge.LadyRouge.models;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.text.SimpleDateFormat;  
import java.util.Date;  

import com.ladyrouge.LadyRouge.services.apiServices.DTO.Factura.FacturaJsonDTO;

@Data
@Entity
@Log4j2
@Table(name = "factura", schema = "public")
public class Factura {
    
    @Id
    @Column(name="factura_id")
    @GeneratedValue(generator = "factura_id_seq")
    @SequenceGenerator(name = "factura_id_seq", sequenceName = "public.factura_id_seq", allocationSize = 1)
    private Long facturaId;

    @Column(name="FECHACOMPRA")
    private Date fechaCompra;

    @Column(name="FECHAFACTURA")
    private Date fechaFactura;

    @Column(name="COSTOTOTAL")
    private double costoTotal;

    @Column(name="COSTODESPACHO")
    private double costoDespacho;

    @Column(name="COSTOIMPUESTO")
    private double costoImpuesto;

    @Column(name="FECHALLEGADAESTIMADA")
    private Date fechaLlegadaEstimada;

    @Column(name="FECHAESTIMADA")
    private Date fechaEstimada;

    @Column(name="")
    private Boolean estadoFactura;

    @Column(name="CREATEDAT")
    private Date createdAt;

    @Column(name="UPDATEDAT")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "proveedor_id", referencedColumnName="proveedor_id")
    private Proveedor proveedor;

    public Factura(FacturaJsonDTO factura){
        SimpleDateFormat formatter1=new SimpleDateFormat("dd-MM-yyyy");
        try{
            this.fechaCompra = formatter1.parse(factura.getFechaCompra());
            this.fechaFactura = formatter1.parse(factura.getFechaFactura());
            this.fechaLlegadaEstimada = formatter1.parse(factura.getFechaLlegadaEstimada());
            this.fechaEstimada = formatter1.parse(factura.getFechaEstimada());
            this.createdAt = new Date(System.currentTimeMillis());
            this.updatedAt = new Date(System.currentTimeMillis());
            this.costoTotal = factura.getCostoTotal();
            this.costoDespacho = factura.getCostoDespacho();
            this.costoImpuesto = factura.getCostoImpuesto();
            this.estadoFactura = factura.getEstadoFactura();
        }catch(Exception e){
            log.error("[Factura Entity][constructor] - Error al construir la Factura");
        }
    }

    public Factura(){
    }
}
