package com.rose.productservice.service;

import com.rose.productservice.model.Product;
import com.rose.productservice.model.dto.ProductRequest;
import com.rose.productservice.model.dto.ProductResponse;
import com.rose.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .description(
                        productRequest.getName()
                )
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
      return  products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return  ProductResponse.builder()
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .id(product.getId())
                .build();
    }
}
