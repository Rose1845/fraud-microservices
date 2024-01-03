package com.rose.productservice.controller;

import com.rose.productservice.model.dto.ProductRequest;
import com.rose.productservice.model.dto.ProductResponse;
import com.rose.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
    private void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }
    @GetMapping
    private List<ProductResponse> getAllProducts(){
      return  productService.getAllProducts();
    }

}
