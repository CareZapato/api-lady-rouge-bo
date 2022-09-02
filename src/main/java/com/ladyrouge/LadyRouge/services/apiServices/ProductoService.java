package com.ladyrouge.LadyRouge.services.apiServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.ladyrouge.LadyRouge.models.Producto;
import com.ladyrouge.LadyRouge.repositories.CategoriaRepository;
import com.ladyrouge.LadyRouge.repositories.ColorRepository;
import com.ladyrouge.LadyRouge.repositories.ProductoRepository;
import com.ladyrouge.LadyRouge.repositories.StatusProductoRepository;
import com.ladyrouge.LadyRouge.repositories.TallaRepository;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.InsertarProductoResponse;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private TallaRepository tallaRepository;

    @Autowired
    private StatusProductoRepository statusProductoRepository;

    
    public Iterable<Producto> listaProductos(){
        return productoRepository.findAll();
    }

    public Iterable<Producto> findByNombre(String productoEntrada){
        return productoRepository.findByNombre(productoEntrada);
    }

    public InsertarProductoResponse insertarProducto(Producto producto){
        InsertarProductoResponse response = new InsertarProductoResponse();
        if(Iterables.size(productoRepository.findByNombre(producto.getNombre())) == 0){
            Producto nuevaProducto = new Producto(producto);
            nuevaProducto.setCategoria(categoriaRepository.findById(producto.getCategoria().getCategoriaId()).get());
            nuevaProducto.setTalla(tallaRepository.findById(producto.getTalla().getTallaId()).get());
            nuevaProducto.setColor(colorRepository.findById(producto.getColor().getColorId()).get());
            nuevaProducto.setStatusproducto(statusProductoRepository.findById(producto.getStatusproducto().getStatusProductoId()).get());
            try{
                productoRepository.save(nuevaProducto);
                response.setResponse("Inserción correcta");
                response.setProducto(nuevaProducto);
            }catch(Exception e){
                response.setResponse("Error inserción");
                response.setError(e.toString());
                response.setProducto(nuevaProducto);
                return response;
            }
        }else{
            response.setResponse("Un producto ya se encuentra ingresdo con ese nombre. No se hace inserción");
        }
        return response;
    }

    public InsertarProductoResponse eliminarProductos(){
        InsertarProductoResponse response = new InsertarProductoResponse();
        try{
            Long cant = colorRepository.count();
            colorRepository.deleteAll();
            response.setEliminados(cant);
            response.setResponse("Se eliminaron todos los productos de la Base de datos");
        }catch(Exception e){
            response.setResponse("Error al insertar productos");
            response.setError(e.toString());
            return response;
        }
        return response;
    }
}