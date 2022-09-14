package com.ladyrouge.LadyRouge.services.apiServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ladyrouge.LadyRouge.models.Factura;
import com.ladyrouge.LadyRouge.models.Stock;
import com.ladyrouge.LadyRouge.repositories.FacturaRepository;
import com.ladyrouge.LadyRouge.repositories.ProductoRepository;
import com.ladyrouge.LadyRouge.repositories.ProveedorRepository;
import com.ladyrouge.LadyRouge.repositories.StockRepository;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.Factura.FacturaJsonDTO;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.Factura.InsertarFacturaResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private StockRepository stockRepository;

    
    public Iterable<Factura> listaFacturas(){
        return facturaRepository.findAll();
    }

    @Transactional
    public InsertarFacturaResponse insertarFactura(FacturaJsonDTO facturaJsonDTO){
        InsertarFacturaResponse response = new InsertarFacturaResponse();
        Factura nuevaFactura = new Factura(facturaJsonDTO);
        nuevaFactura.setProveedor(proveedorRepository.findById(facturaJsonDTO.getProveedor().getProveedorId()).get());    
        int insertados = 0;
        int errores = 0;
        try{ // try save and flush Factura
            Factura facturaGuardada = facturaRepository.saveAndFlush(nuevaFactura);
            log.info("[FacturaService][insertarFactura] - Factura guardada con id: "+facturaGuardada.getFacturaId());
            for(int i=0; i<facturaJsonDTO.getStocks().length; i++){
                Stock stockNuevo = new Stock(facturaJsonDTO.getStocks()[i]);
                stockNuevo.setProducto(productoRepository.findById(facturaJsonDTO.getStocks()[i].getProducto().getProductoId()).get());
                stockNuevo.setFactura(facturaGuardada);
                try{ // try save and flush Stocks
                    stockRepository.save(stockNuevo);
                    log.info("[FacturaService][insertarFactura] - Se agrega al Stock el producto");
                    insertados++;

                }catch(Exception e){
                    errores++;
                    response.setResponse("Error al guardar la Stock con id: "+facturaJsonDTO.getStocks()[i]);
                    response.setError(e.toString());
                    log.error("[FacturaService][insertarFactura] - Error al agregar al stock el producto: "+facturaJsonDTO.getStocks()[i].getStockId());
                }
                
            }
            response.setInsertados(insertados);
            response.setErrores(errores);
        }
        catch(Exception e){
            response.setResponse("Error al guardar la Factura");
            response.setError(e.toString());
            log.error("[FacturaService][insertarFactura] - Error al insertar la Factura nueva");
            return response;
        } 
        String s = insertados > 0 ? "s" : "";
        response.setResponse("Proceso de insersion de Factura exitoso con "+insertados+" producto"+s+" agregado"+s+".");
        return response;
    }
}