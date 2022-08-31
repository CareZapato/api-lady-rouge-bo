package com.ladyrouge.LadyRouge.services.apiServices.DTO;

import com.ladyrouge.LadyRouge.models.Product;

import lombok.Data;

@Data
public class InsertarProductsResponse {
    String response;
    String error;
    Product product;
}