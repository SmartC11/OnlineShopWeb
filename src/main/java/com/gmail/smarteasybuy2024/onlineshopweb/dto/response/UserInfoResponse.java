package com.gmail.smarteasybuy2024.onlineshopweb.dto.response;

import com.gmail.smarteasybuy2024.onlineshopweb.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class UserInfoResponse {
    private User user;
}

