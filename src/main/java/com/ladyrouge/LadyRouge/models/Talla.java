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
@Table(name = "talla", schema = "public")
public class Talla implements Serializable {

    public static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="talla_id")
    @GeneratedValue(generator = "talla_id_seq")
    @SequenceGenerator(name = "talla_id_seq", sequenceName = "public.talla_id_seq", allocationSize = 1)
    private Long tallaId;
    
    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="DESCRIPCION")
    private String descripcion;

    @Column(name="CREATEDAT")
    private Date createdAt;

    @Column(name="UPDATEDAT")
    private Date updatedAt;

    @OneToMany(cascade= CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    @JsonIgnore
    private Set<Producto> productos;

    public Talla(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public Talla(){
    }

}