package com.ecommerce.productservice.services;

import com.ecommerce.productservice.dtos.FakeStoreAPIResponseDTO;
import com.ecommerce.productservice.dtos.FakeStoreAPIRequestDTO;
import com.ecommerce.productservice.exceptions.ProductNotFoundException;
import com.ecommerce.productservice.models.Category;
import com.ecommerce.productservice.models.Product;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreAPIService implements ProductService {

    private RestTemplate restTemplate;

    public FakeStoreAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) throws ProductNotFoundException {
        ResponseEntity<FakeStoreAPIResponseDTO> responseEntity =
                restTemplate.getForEntity("https://fakestoreapi.com/products/" + id,
                        FakeStoreAPIResponseDTO.class);

//        FakeStoreAPIResponseDTO responseDTO = restTemplate.getForObject(
//                "https://fakestoreapi.com/products/" + id,
//                FakeStoreAPIResponseDTO.class
//        );

        FakeStoreAPIResponseDTO responseDTO = responseEntity.getBody();

//        if (responseDTO == null) {
//            throw new ProductNotFoundException("Product not found!");
//        }

        ResponseEntity<Product> productResponseEntity =
                new ResponseEntity<>(responseEntity.getBody().toProduct(),  HttpStatus.OK);

        return productResponseEntity;
    }

    @Override
    public Product createProduct(String title, String description,
                                 double price, String image, String category) {

        FakeStoreAPIRequestDTO requestDTO = new FakeStoreAPIRequestDTO(
                title,
                description,
                price,
                image,
                category
        );

        FakeStoreAPIResponseDTO responseDTO = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                requestDTO,
                FakeStoreAPIResponseDTO.class
        );

        return responseDTO.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreAPIResponseDTO[] responseDTOS = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreAPIResponseDTO[].class);

        List<Product> productList = new ArrayList<>();

        for (FakeStoreAPIResponseDTO responseDTO : responseDTOS) {
            productList.add(responseDTO.toProduct());
        }

        return productList;
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        FakeStoreAPIRequestDTO fakeStoreRequest = product.toFakeAPIRequestDTO();

        HttpEntity<FakeStoreAPIRequestDTO> requestEntity =
                new HttpEntity<>(fakeStoreRequest);

        ResponseEntity<FakeStoreAPIResponseDTO> responseDTO = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreAPIResponseDTO.class
        );

        return responseDTO.getBody().toProduct();

    }

    @Override
    public ResponseEntity<Product> deleteProductById(Long id) throws ProductNotFoundException {
        ResponseEntity<FakeStoreAPIResponseDTO> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.DELETE,
                null,
                FakeStoreAPIResponseDTO.class
        );

        // responseEntity.getBody() --> FakeStoreAPIResponseDTO.class
        // responseEntity.getStatusCode() --> status code 200, 400, 404 etc.,

        FakeStoreAPIResponseDTO responseDTO = responseEntity.getBody();

        if(responseDTO == null){
            throw new ProductNotFoundException("Product not found");
        }

        ResponseEntity<Product> productResponseEntity =
                new ResponseEntity<>(responseDTO.toProduct(),  HttpStatus.OK);

        return productResponseEntity;
    }
}
