package com.ecommerce.productservice.services;

import com.ecommerce.productservice.dtos.FakeStoreAPIResponseDTO;
import com.ecommerce.productservice.dtos.FakeStoreAPIRequestDTO;
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
    public ResponseEntity<Product> getProductById(Long id) {
        ResponseEntity<FakeStoreAPIResponseDTO> responseEntity =
                restTemplate.getForEntity("https://fakestoreapi.com/products/" + id,
                        FakeStoreAPIResponseDTO.class);

//        FakeStoreAPIResponseDTO responseDTO = restTemplate.getForObject(
//                "https://fakestoreapi.com/products/" + id,
//                FakeStoreAPIResponseDTO.class
//        );

        if(responseEntity.getBody() == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id - " + id + " not found");
        }

        ResponseEntity<Product> productResponseEntity =
                new ResponseEntity<>(responseEntity.getBody().toProduct(),  HttpStatus.OK);

        return productResponseEntity;
    }

    @Override
    public Product createProduct(String title, String description,
                                 double price, String image, String category) {

        FakeStoreAPIRequestDTO requestDTO = new FakeStoreAPIRequestDTO();
        requestDTO.setTitle(title);
        requestDTO.setDescription(description);
        requestDTO.setPrice(price);
        requestDTO.setImage(image);
        requestDTO.setCategory(category);

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
    public ResponseEntity<Product> updateProduct(Long id, Product product) {

        FakeStoreAPIRequestDTO requestDTO = new FakeStoreAPIRequestDTO();
        requestDTO.setTitle(product.getTitle());
        requestDTO.setDescription(product.getDescription());
        requestDTO.setPrice(product.getPrice());
        requestDTO.setImage(product.getImageURL());
        requestDTO.setCategory(product.getCategory().getName());

        HttpEntity<FakeStoreAPIRequestDTO> requestEntity
                = new HttpEntity<>(requestDTO);

        ResponseEntity<FakeStoreAPIResponseDTO> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreAPIResponseDTO.class);

        ResponseEntity<Product> productResponseEntity =
                new ResponseEntity<>(responseEntity.getBody().toProduct(),  HttpStatus.OK);

        return productResponseEntity;
    }
}
