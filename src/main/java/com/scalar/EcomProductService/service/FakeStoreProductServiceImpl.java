package com.scalar.EcomProductService.service;

import com.scalar.EcomProductService.dto.ProductListResponseDTO;
import com.scalar.EcomProductService.dto.ProductRequestDTO;
import com.scalar.EcomProductService.dto.ProductResponseDTO;
import com.scalar.EcomProductService.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {
        String getAllProductsURL = "https://fakestoreapi.com/products";
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<ProductResponseDTO[]> productResponse =
                restTemplate.getForEntity(getAllProductsURL, ProductResponseDTO[].class);
        ProductListResponseDTO responseDTO = new ProductListResponseDTO();
        for(ProductResponseDTO productResponseDTO : productResponse.getBody()){
            responseDTO.getProducts().add(productResponseDTO);
        }

        return responseDTO;
    }

    @Override
    public ProductResponseDTO getProductById(int id) {
        String getAllProductsURL = "https://fakestoreapi.com/products/"+ id;
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<ProductResponseDTO> productResponse =
                restTemplate.getForEntity(getAllProductsURL, ProductResponseDTO.class);
        return productResponse.getBody();
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO product) {
        String createProductUrl = "https://fakestoreapi.com/products";

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<ProductResponseDTO> response =
                restTemplate.postForEntity(createProductUrl, product, ProductResponseDTO.class);
        return response.getBody();
    }

    @Override
    public boolean deleteProduct(int id) {
        String deleteProductUrl = "https://fakestoreapi.com/products/"+id;

        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(deleteProductUrl);
        return true;
    }

    @Override
    public Product updateProduct(int id, Product product) {
        return null;
    }
}
