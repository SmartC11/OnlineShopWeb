package com.gmail.smarteasybuy2024.onlineshopweb.service.impl;

import com.gmail.smarteasybuy2024.onlineshopweb.constants.ErrorMessage;
import com.gmail.smarteasybuy2024.onlineshopweb.domain.Product;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.request.SearchRequest;
import com.gmail.smarteasybuy2024.onlineshopweb.repository.ProductRepository;
import com.gmail.smarteasybuy2024.onlineshopweb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.PRODUCT_NOT_FOUND));
    }

    @Override
    public List<Product> getPopularProducts() {
        List<Long> productIds = Arrays.asList(26L, 43L, 46L, 106L, 34L, 76L, 82L, 85L, 27L, 39L, 79L, 86L);
        return productRepository.findByIdIn(productIds);
    }

    @Override
    public Page<Product> getProductsByFilterParams(SearchRequest request, Pageable pageable) {
        Integer startingPrice = request.getPrice();
        Integer endingPrice = startingPrice + (startingPrice == 0 ? 30000 : 4999);
        return productRepository.getProductsByFilterParams(
                request.getCategories(),
                startingPrice,
                endingPrice,
                request.getReleaseYears(),
                pageable);
    }

    @Override
    public Page<Product> searchProducts(SearchRequest request, Pageable pageable) {
        return productRepository.searchProducts(request.getSearchType(), request.getText(), pageable);
    }
}

