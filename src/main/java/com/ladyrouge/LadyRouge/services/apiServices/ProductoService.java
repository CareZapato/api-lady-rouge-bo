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
import com.ladyrouge.LadyRouge.services.apiServices.DTO.Producto.InsertarProductoResponse;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.Producto.InsertarProductosResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
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
                log.error("[ProductoService][insertarProducto] - Error al insertar producto");
                return response;
            }
        }else{
            response.setResponse("Un producto ya se encuentra ingresdo con ese nombre. No se hace inserción");
        }
        return response;
    }

    public InsertarProductosResponse insertarProductos(Producto[] producto){
        InsertarProductosResponse response = new InsertarProductosResponse();
        int insertados = 0;
        int repetidos = 0;
        int errores = 0;

        
        for(int i=0; i<producto.length; i++){
            Iterable<Producto> prods = productoRepository.findByNombreAndTallaTallaIdAndCategoriaCategoriaIdAndColorColorId(
            producto[i].getNombre(),
            producto[i].getTalla().getTallaId(),
            producto[i].getCategoria().getCategoriaId(),
            producto[i].getColor().getColorId()
        );
            if(Iterables.size(prods) == 0){
                Producto nuevaProducto = new Producto(producto[i]);
                nuevaProducto.setCategoria(categoriaRepository.findById(producto[i].getCategoria().getCategoriaId()).get());
                nuevaProducto.setTalla(tallaRepository.findById(producto[i].getTalla().getTallaId()).get());
                nuevaProducto.setColor(colorRepository.findById(producto[i].getColor().getColorId()).get());
                nuevaProducto.setStatusproducto(statusProductoRepository.findById(producto[i].getStatusproducto().getStatusProductoId()).get());
                try{
                    productoRepository.save(nuevaProducto);
                    insertados++;
                    log.info("[ProductoService][insertarProductos] - producto "+producto[i].toString()+" insertado correctamente");
                }catch(Exception e){
                    errores++;
                    log.error("[ProductoService][insertarProducto] - Error al insertar producto");
                    response.setError("Error al insertar producto "+producto[i].toString());
                    return response;
                }
            }else{
                repetidos++;
                log.info("[ProductoService][insertarProductos] - ya existia el producto "+producto[i].getNombre()+" y no fue necesario insertarlo");
            }
        }
        response.setInsertados(insertados);
        response.setRepetidos(repetidos);
        response.setErrores(errores);
        response.setResponse("Se generaron las transacciones. Revisar valores");
        
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
            response.setResponse("Error al eliminar productos");
            response.setError(e.toString());
            log.error("[ProductoService][eliminarProductos] - Error al eliminar productos");
            return response;
        }
        return response;
    }
}