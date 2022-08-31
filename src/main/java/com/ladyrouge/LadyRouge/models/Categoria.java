package com.ladyrouge.LadyRouge.models;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categoria", schema = "public")
public class Categoria implements Serializable {

    public static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="categoria_id")
    @GeneratedValue(generator = "categoria_id_seq")
    @SequenceGenerator(name = "categoria_id_seq", sequenceName = "public.categoria_id_seq", allocationSize = 1)
    private int categoriaId;
    
    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="DESCRIPCION")
    private String descripcion;

    @Column(name="ACTIVADO")
    private Boolean activado;

    @Column(name="CREATEDAT")
    private Date createdAt;

    @Column(name="UPDATEDAT")
    private Date updatedAt;

    @OneToMany(cascade= CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private Set<Product> products;

    public Categoria(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activado = true;
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public Categoria(){
    }

}