package com.gmail.smarteasybuy2024.onlineshopweb.controller;

import com.gmail.smarteasybuy2024.onlineshopweb.constants.Pages;
import com.gmail.smarteasybuy2024.onlineshopweb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("products", productService.getPopularProducts());
        return Pages.HOME;
    }
}

