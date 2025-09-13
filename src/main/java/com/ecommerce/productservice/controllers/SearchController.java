package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.dtos.SearchRequestDTO;
import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/search")
    public Page<Product> search(@RequestBody SearchRequestDTO searchRequestDTO){
        // search implementation
        return searchService.search(searchRequestDTO.getQuery(),
                searchRequestDTO.getPageNumber(),
                searchRequestDTO.getPageSize());
    }

}
