package com.ladyrouge.LadyRouge.models;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@Table(name = "categoria", schema = "public")
public class Categoria implements Serializable {

    public static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="categoria_id")
    @GeneratedValue(generator = "categoria_id_seq")
    @SequenceGenerator(name = "categoria_id_seq", sequenceName = "public.categoria_id_seq", allocationSize = 1)
    private Long categoriaId;
    
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

    @OneToMany(cascade= CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    @JsonIgnore
    private Set<Producto> productos;

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