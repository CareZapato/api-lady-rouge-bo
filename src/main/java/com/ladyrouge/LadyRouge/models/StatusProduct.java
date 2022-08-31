package com.ladyrouge.LadyRouge.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "statusproduct", schema = "public")
public class StatusProduct implements Serializable {
    @Id
    @Column(name="statusproduct_id")
    @GeneratedValue(generator = "statusproduct_id_seq")
    @SequenceGenerator(name = "statusproduct_id_seq", sequenceName = "public.statusproduct_id_seq", allocationSize = 1)
    private int statusProductId;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="DESCRIPCION")
    private String descripcion;

    @Column(name="CREATEDAT")
    private Date createdAt;

    @Column(name="UPDATEDAT")
    private Date updatedAt;

    @OneToMany(cascade= CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private Set<Product> products;

    public StatusProduct(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public StatusProduct(){
    }
}
