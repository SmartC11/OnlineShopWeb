package com.gmail.smarteasybuy2024.onlineshopweb.service;

import com.gmail.smarteasybuy2024.onlineshopweb.domain.Order;
import com.gmail.smarteasybuy2024.onlineshopweb.domain.Product;
import com.gmail.smarteasybuy2024.onlineshopweb.domain.User;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.request.OrderRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Order getOrder(Long orderId);

    List<Product> getOrdering();

    Page<Order> getUserOrdersList(Pageable pageable);

    Long postOrder(User user, OrderRequest orderRequest);
}

