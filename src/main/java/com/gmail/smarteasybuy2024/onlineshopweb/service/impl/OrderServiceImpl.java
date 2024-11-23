package com.gmail.smarteasybuy2024.onlineshopweb.service.impl;

import com.gmail.smarteasybuy2024.onlineshopweb.constants.ErrorMessage;
import com.gmail.smarteasybuy2024.onlineshopweb.domain.Order;
import com.gmail.smarteasybuy2024.onlineshopweb.domain.Product;
import com.gmail.smarteasybuy2024.onlineshopweb.domain.User;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.request.OrderRequest;
import com.gmail.smarteasybuy2024.onlineshopweb.repository.OrderRepository;
import com.gmail.smarteasybuy2024.onlineshopweb.service.OrderService;
import com.gmail.smarteasybuy2024.onlineshopweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final MailService mailService;

    @Override
    public Order getOrder(Long orderId) {
        User user = userService.getAuthenticatedUser();
        return orderRepository.getByIdAndUserId(orderId, user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.ORDER_NOT_FOUND));
    }

    @Override
    public List<Product> getOrdering() {
        User user = userService.getAuthenticatedUser();
        return user.getProductList();
    }

    @Override
    public Page<Order> getUserOrdersList(Pageable pageable) {
        User user = userService.getAuthenticatedUser();
        return orderRepository.findOrderByUserId(user.getId(), pageable);
    }

    @Override
    @Transactional
    public Long postOrder(User user, OrderRequest orderRequest) {
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setUser(user);
        order.getProducts().addAll(user.getProductList());
        orderRepository.save(order);
        user.getProductList().clear();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("order", order);
        mailService.sendMessageHtml(order.getEmail(), "Order #" + order.getId(), "order-template", attributes);
        return order.getId();
    }
}

