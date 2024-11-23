package com.gmail.smarteasybuy2024.onlineshopweb.service.impl;

import com.gmail.smarteasybuy2024.onlineshopweb.constants.ErrorMessage;
import com.gmail.smarteasybuy2024.onlineshopweb.constants.SuccessMessage;
import com.gmail.smarteasybuy2024.onlineshopweb.domain.Order;
import com.gmail.smarteasybuy2024.onlineshopweb.domain.Product;
import com.gmail.smarteasybuy2024.onlineshopweb.domain.User;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.request.ProductRequest;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.request.SearchRequest;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.response.MessageResponse;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.response.UserInfoResponse;
import com.gmail.smarteasybuy2024.onlineshopweb.repository.OrderRepository;
import com.gmail.smarteasybuy2024.onlineshopweb.repository.ProductRepository;
import com.gmail.smarteasybuy2024.onlineshopweb.repository.UserRepository;
import com.gmail.smarteasybuy2024.onlineshopweb.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Value("${upload.path}")
    private String uploadPath;

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAllByOrderByPriceAsc(pageable);
    }

    @Override
    public Page<Product> searchProducts(SearchRequest request, Pageable pageable) {
        return productRepository.searchProducts(request.getSearchType(), request.getText(), pageable);
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> searchUsers(SearchRequest request, Pageable pageable) {
        return userRepository.searchUsers(request.getSearchType(), request.getText(), pageable);
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.getById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.ORDER_NOT_FOUND));
    }

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);

    }

    @Override
    public Page<Order> searchOrders(SearchRequest request, Pageable pageable) {
        return orderRepository.searchOrders(request.getSearchType(), request.getText(), pageable);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.PRODUCT_NOT_FOUND));
    }

    @Override
    @SneakyThrows
    @Transactional
    public MessageResponse editProduct(ProductRequest productRequest, MultipartFile file) {
        return saveProduct(productRequest, file, SuccessMessage.PRODUCT_EDITED);
    }

    @Override
    @SneakyThrows
    @Transactional
    public MessageResponse addProduct(ProductRequest productRequest, MultipartFile file) {
        return saveProduct(productRequest, file, SuccessMessage.PRODUCT_ADDED);
    }

    @Override
    public UserInfoResponse getUserById(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.USER_NOT_FOUND));
        Page<Order> orders = orderRepository.findOrderByUserId(userId, pageable);
        return new UserInfoResponse(user, orders);
    }

    private MessageResponse saveProduct(ProductRequest productRequest, MultipartFile file, String message) throws IOException {
        Product product = modelMapper.map(productRequest, Product.class);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            product.setFilename(resultFilename);
        }
        productRepository.save(product);
        return new MessageResponse("alert-success", message);
    }
}

