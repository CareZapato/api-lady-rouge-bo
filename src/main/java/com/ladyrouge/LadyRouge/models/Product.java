package com.ladyrouge.LadyRouge.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "product", schema = "public")
public class Product implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @Column(name="product_id")
    @GeneratedValue(generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "public.product_id_seq", allocationSize = 1)
    private int productId;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="DESCRIPCION")
    private String descripcion;

    @Column(name="COSTOINDIVIDUAL")
    private String costoIndividual;

    @Column(name="FECHAINGRESO")
    private Date fechaIngreso;

    @Column(name="PRECIOBASE")
    private String precioBase;

    @Column(name="COSTOCOMPRA")
    private String costoCompra;

    @Column(name="ACTIVADO")
    private Boolean activado;

    @ManyToOne()
    @JoinColumn(name="talla_id")
    private Talla talla;

    @ManyToOne()
    @JoinColumn(name="statusproduct_id")
    private StatusProduct statusproduct;

    @ManyToOne()
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

    @ManyToOne()
    @JoinColumn(name="color_id")
    private Color color;

    @Column(name="CREATEDAT")
    private Date createdAt;

    @Column(name="UPDATEDAT")
    private Date updatedAt;

    public Product(Product product){
        // Valores Producto
        this.nombre = product.getNombre();
        this.descripcion = product.getDescripcion();
        this.precioBase = product.getPrecioBase();
        this.costoIndividual = product.getCostoIndividual();
        this.costoCompra = product.getCostoCompra();
        this.activado = true;
        this.fechaIngreso = new Date(System.currentTimeMillis());
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public Product(){
    }
}
