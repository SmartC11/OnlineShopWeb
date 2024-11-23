package com.gmail.smarteasybuy2024.onlineshopweb.service;

import com.gmail.smarteasybuy2024.onlineshopweb.domain.Product;

import java.util.List;

public interface CartService {

    List<Product> getProductsInCart();

    void addProductToCart(Long productId);

    void removeProductFromCart(Long productId);
}
