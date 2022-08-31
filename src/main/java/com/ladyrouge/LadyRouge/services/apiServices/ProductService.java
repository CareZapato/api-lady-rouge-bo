package com.ladyrouge.LadyRouge.services.apiServices;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.ladyrouge.LadyRouge.models.Categoria;
import com.ladyrouge.LadyRouge.models.Color;
import com.ladyrouge.LadyRouge.models.Product;
import com.ladyrouge.LadyRouge.models.StatusProduct;
import com.ladyrouge.LadyRouge.models.Talla;
import com.ladyrouge.LadyRouge.repositories.CategoriaRepository;
import com.ladyrouge.LadyRouge.repositories.ColorRepository;
import com.ladyrouge.LadyRouge.repositories.ProductRepository;
import com.ladyrouge.LadyRouge.repositories.StatusProductRepository;
import com.ladyrouge.LadyRouge.repositories.TallaRepository;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.InsertarProductsResponse;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private TallaRepository tallaRepository;

    @Autowired
    private StatusProductRepository statusProductRepository;

    
    // public Iterable<Product> listaProducts(){
    //     return productRepository.findAll();
    // }

    // public Iterable<Product> findByNombre(String productEntrada){
    //     return productRepository.findByNombre(productEntrada);
    // }

    public InsertarProductsResponse insertarProduct(Product product){
        InsertarProductsResponse response = new InsertarProductsResponse();

        if(Iterables.size(productRepository.findByNombre(product.getNombre())) == 0){
            Product nuevaProduct = new Product(product);
            
            try{
                productRepository.save(nuevaProduct);
            }catch(Exception e){
                response.setResponse("Error inserción");
                return response;
            }
            // List<ColorProduct> colores = new ArrayList<ColorProduct>();
            // for(ProductColor color: product.getProductColor()){
            //     Optional<Color> find = colorRepository.findById(Long.valueOf(color.getProductColorId()));
            //     if(!find.isEmpty()){
            //         ProductColor productColor = new ProductColor(find.get());
            //     }
            // }
            // List<Categoria> categorias = new ArrayList<Categoria>();
            // for(ProductCategoria categoria: product.getProductCategoria()){
            //     Optional<Categoria> find = categoriaRepository.findById(Long.valueOf(categoria.getProductCategoriaId()));
            //     if(!find.isEmpty()){
            //         categorias.add(find.get());
            //     }
            // }
            // List<Talla> tallas = new ArrayList<Talla>();
            // for(ProductTalla talla: product.getProductTalla()){
            //     Optional<Talla> find = tallaRepository.findById(Long.valueOf(talla.getProductTallaId()));
            //     if(!find.isEmpty()){
            //         tallas.add(find.get());
            //     }
            // }
            // List<StatusProduct> statusProducts = new ArrayList<StatusProduct>();
            // for(ProductStatusProduct statusProduct: product.getProductStatusProduct()){
            //     Optional<StatusProduct> find = statusProductRepository.findById(Long.valueOf(statusProduct.getProductStatusProductId()));
            //     if(!find.isEmpty()){
            //         statusProducts.add(find.get());
            //     }
            // }
            // try{
            //     Set<Color> colors = new HashSet<>(colores);
            //     nuevaProduct.setProductColor(colors);
            //     productRepository.save(nuevaProduct);
            //     response.setNuevos(response.getNuevos() + 1);
            // }
            // catch(Exception e){
            //     response.setResponse("Error al insertar products");
            //     response.setError(e.toString());
            //     return response;
            // }
        }
        response.setResponse("Inserción correcta");
        return response;
    }

    // public InsertarProductsResponse eliminarProducts(){
    //     InsertarProductsResponse response = new InsertarProductsResponse();
    //     try{
    //         Long cant = productRepository.count();
    //         productRepository.deleteAll();
    //         response.setEliminados(cant);
    //         response.setResponse("Se eliminaron todos las products de la Base de datos");
    //     }catch(Exception e){
    //         response.setResponse("Error al insertar products");
    //         response.setError(e.toString());
    //         return response;
    //     }
    //     return response;
    // }
}