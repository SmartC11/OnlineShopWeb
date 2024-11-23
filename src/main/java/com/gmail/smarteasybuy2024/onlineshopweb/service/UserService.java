package com.gmail.smarteasybuy2024.onlineshopweb.service;

import com.gmail.smarteasybuy2024.onlineshopweb.domain.Order;
import com.gmail.smarteasybuy2024.onlineshopweb.domain.User;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.request.ChangePasswordRequest;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.request.EditUserRequest;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.request.SearchRequest;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User getAuthenticatedUser();

    Page<Order> searchUserOrders(SearchRequest request, Pageable pageable);

    MessageResponse editUserInfo(EditUserRequest request);

    MessageResponse changePassword(ChangePasswordRequest request);
}

