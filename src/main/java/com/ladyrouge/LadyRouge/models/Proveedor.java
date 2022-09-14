package com.ladyrouge.LadyRouge.models;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.text.SimpleDateFormat;  
import java.util.Date;  

@Data
@Entity
@Table(name = "proveedor", schema = "public")
public class Proveedor {
    
    @Id
    @Column(name="proveedor_id")
    @GeneratedValue(generator = "proveedor_id_seq")
    @SequenceGenerator(name = "proveedor_id_seq", sequenceName = "public.proveedor_id_seq", allocationSize = 1)
    private Long proveedorId;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="EMAIL")
    private String email;

    @Column(name="PAIS")
    private String pais;

    @Column(name="CIUDAD")
    private String ciudad;

    @Column(name="DIRECCION")
    private String direccion;

    @Column(name="TELEFONO")
    private String telefono;

    @Column(name="WSP")
    private String wsp;

    @Column(name="NOMBREREPRESENTANTE")
    private String nombreRepresentante;

    @Column(name="WEB")
    private String web;

    @Column(name="ESTADO")
    private String estado;

    @Column(name="DESCRIPCION")
    private String descripcion;

    @Column(name="CREATEDAT")
    private Date createdAt;

    @Column(name="UPDATEDAT")
    private Date updatedAt;

    public Proveedor(Proveedor proveedor){
        this.nombre = proveedor.getNombre();
        this.email = proveedor.getEmail();
        this.pais = proveedor.getPais();
        this.ciudad = proveedor.getCiudad();
        this.direccion = proveedor.getDireccion();
        this.telefono = proveedor.getTelefono();
        this.wsp = proveedor.getWsp();
        this.nombreRepresentante = proveedor.getNombreRepresentante();
        this.web = proveedor.getWeb();
        this.estado = proveedor.getEstado();
        this.descripcion = proveedor.getDescripcion();
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public Proveedor(){
    }
}
