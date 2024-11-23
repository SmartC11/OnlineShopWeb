package com.gmail.smarteasybuy2024.onlineshopweb.controller;

import com.gmail.smarteasybuy2024.onlineshopweb.constants.Pages;
import com.gmail.smarteasybuy2024.onlineshopweb.constants.PathConstants;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.request.SearchRequest;
import com.gmail.smarteasybuy2024.onlineshopweb.service.ProductService;
import com.gmail.smarteasybuy2024.onlineshopweb.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(PathConstants.PRODUCT)
public class ProductController {

    private final ProductService productService;
    private final ControllerUtils controllerUtils;

    @GetMapping("/{productId}")
    public String getProductById(@PathVariable Long productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return Pages.PRODUCT;
    }

    @GetMapping
    public String getProductsByFilterParams(SearchRequest request, Model model, Pageable pageable) {
        controllerUtils.addPagination(request, model, productService.getProductsByFilterParams(request, pageable));
        return Pages.PRODUCTS;
    }

    @GetMapping("/search")
    public String searchProducts(SearchRequest request, Model model, Pageable pageable) {
        controllerUtils.addPagination(request, model, productService.searchProducts(request, pageable));
        return Pages.PRODUCTS;
    }
}
