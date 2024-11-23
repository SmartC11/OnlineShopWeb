package com.gmail.smarteasybuy2024.onlineshopweb.service;

import com.gmail.smarteasybuy2024.onlineshopweb.domain.Order;
import com.gmail.smarteasybuy2024.onlineshopweb.domain.Product;
import com.gmail.smarteasybuy2024.onlineshopweb.domain.User;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.request.ProductRequest;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.request.SearchRequest;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.response.MessageResponse;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.response.UserInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface AdminService {

    Page<Product> getProducts(Pageable pageable);

    Page<Product> searchProducts(SearchRequest request, Pageable pageable);

    Page<User> getUsers(Pageable pageable);

    Page<User> searchUsers(SearchRequest request, Pageable pageable);

    Order getOrder(Long orderId);

    Page<Order> getOrders(Pageable pageable);

    Page<Order> searchOrders(SearchRequest request, Pageable pageable);

    Product getProductById(Long productId);

    MessageResponse editProduct(ProductRequest productRequest, MultipartFile file);

    MessageResponse addProduct(ProductRequest productRequest, MultipartFile file);

    UserInfoResponse getUserById(Long userId, Pageable pageable);
}

