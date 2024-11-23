package com.gmail.smarteasybuy2024.onlineshopweb.service;

import com.gmail.smarteasybuy2024.onlineshopweb.dto.response.MessageResponse;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.request.UserRequest;

public interface RegistrationService {

    MessageResponse registration(String captchaResponse, UserRequest user);

    MessageResponse activateEmailCode(String code);
}
