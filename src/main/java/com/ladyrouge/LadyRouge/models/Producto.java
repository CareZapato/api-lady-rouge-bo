package com.ladyrouge.LadyRouge.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.Optional;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "producto", schema = "public")
public class Producto implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @Column(name="producto_id")
    @GeneratedValue(generator = "producto_id_seq")
    @SequenceGenerator(name = "producto_id_seq", sequenceName = "public.producto_id_seq", allocationSize = 1)
    private Long productoId;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="DESCRIPCION")
    private String descripcion;

    @Column(name="FECHACREACION")
    private Date fechaCreacion;

    @Column(name="ACTIVADO")
    private Boolean activado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="talla_id")
    private Talla talla;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="statusproducto_id")
    private StatusProducto statusproducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="color_id")
    private Color color;

    @Column(name="CREATEDAT")
    private Date createdAt;

    @Column(name="UPDATEDAT")
    private Date updatedAt;

    public Producto(Producto producto){
        // Valores Producto
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.activado = true;
        this.fechaCreacion = new Date(System.currentTimeMillis());
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public Producto(){
    }
}
