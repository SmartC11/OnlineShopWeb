package com.gmail.smarteasybuy2024.onlineshopweb.service;

import com.gmail.smarteasybuy2024.onlineshopweb.domain.Product;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.request.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product getProductById(Long productId);

    List<Product> getPopularProducts();

    Page<Product> getProductsByFilterParams(SearchRequest searchRequest, Pageable pageable);

    Page<Product> searchProducts(SearchRequest searchRequest, Pageable pageable);
}

