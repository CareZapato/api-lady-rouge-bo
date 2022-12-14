package com.ladyrouge.LadyRouge.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "statusproducto", schema = "public")
public class StatusProducto implements Serializable {
    @Id
    @Column(name="statusproducto_id")
    @GeneratedValue(generator = "statusproducto_id_seq")
    @SequenceGenerator(name = "statusproducto_id_seq", sequenceName = "public.statusproducto_id_seq", allocationSize = 1)
    private Long statusProductoId;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="DESCRIPCION")
    private String descripcion;

    @Column(name="CREATEDAT")
    private Date createdAt;

    @Column(name="UPDATEDAT")
    private Date updatedAt;

    public StatusProducto(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public StatusProducto(){
    }
}
