package com.ladyrouge.LadyRouge.models;

import lombok.Data;

import java.sql.Date;

import javax.persistence.*;

@Data
@Entity
@Table(name = "stock", schema = "public")
public class Stock {
    
    @Id
    @Column(name="stock_id")
    @GeneratedValue(generator = "stock_id_seq")
    @SequenceGenerator(name = "stock_id_seq", sequenceName = "public.stock_id_seq", allocationSize = 1)
    private Long stockId;

    @Column(name="CODIGOPRODUCTO")
    private String codigoProducto;

    @Column(name="COSTOUNITARIO")
    private double costoUnitario;

    @Column(name="CALCULOIMPUESTOUNIT")
    private double calculoImpuestoUnit;

    @Column(name="PRECIOVENTA")
    private double precioVenta;

    @Column(name="PRECIOVENTASUGERIDO")
    private double precioVentaSugerido;

    @Column(name="ESTADOSTOCK")
    private String estadoStock;

    @Column(name="CREATEDAT")
    private Date createdAt;

    @Column(name="UPDATEDAT")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "factura_id", referencedColumnName="factura_id")
    private Factura factura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "producto_id", referencedColumnName="producto_id")
    private Producto producto;

    public Stock(Stock stock){
        // Valores stock
        this.costoUnitario = stock.getCostoUnitario();
        this.codigoProducto = stock.getCodigoProducto();
        this.calculoImpuestoUnit = stock.getCalculoImpuestoUnit();
        this.precioVenta = stock.getPrecioVenta();
        this.precioVentaSugerido = stock.getPrecioVentaSugerido();
        this.estadoStock = stock.getEstadoStock();
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public Stock(){
    }
}
