package com.ecommerce.productservice.services;

import com.ecommerce.productservice.dtos.FakeStoreAPIResponseDTO;
import com.ecommerce.productservice.dtos.FakeStoreAPIRequestDTO;
import com.ecommerce.productservice.models.Category;
import com.ecommerce.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreAPIService implements ProductService {

    private RestTemplate restTemplate;

    public FakeStoreAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreAPIResponseDTO responseDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreAPIResponseDTO.class
        );

        return responseDTO.toProduct();
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
}
