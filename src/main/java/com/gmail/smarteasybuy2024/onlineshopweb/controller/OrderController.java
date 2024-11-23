package com.gmail.smarteasybuy2024.onlineshopweb.controller;

import com.gmail.smarteasybuy2024.onlineshopweb.constants.Pages;
import com.gmail.smarteasybuy2024.onlineshopweb.constants.PathConstants;
import com.gmail.smarteasybuy2024.onlineshopweb.domain.User;
import com.gmail.smarteasybuy2024.onlineshopweb.dto.request.OrderRequest;
import com.gmail.smarteasybuy2024.onlineshopweb.service.OrderService;
import com.gmail.smarteasybuy2024.onlineshopweb.service.UserService;
import com.gmail.smarteasybuy2024.onlineshopweb.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping(PathConstants.ORDER)
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ControllerUtils controllerUtils;

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", orderService.getOrder(orderId));
        return Pages.ORDER;
    }

    @GetMapping
    public String getOrdering(Model model) {
        model.addAttribute("products", orderService.getOrdering());
        return Pages.ORDERING;
    }

    @GetMapping("/user/orders")
    public String getUserOrdersList(Model model, Pageable pageable) {
        controllerUtils.addPagination(model, orderService.getUserOrdersList(pageable));
        return Pages.ORDERS;
    }

    @PostMapping
    public String postOrder(@Valid OrderRequest orderRequest, BindingResult bindingResult, Model model) {
        User user = userService.getAuthenticatedUser();
        if (controllerUtils.validateInputFields(bindingResult, model, "products", user.getProductList())) {
            return Pages.ORDERING;
        }
        model.addAttribute("orderId", orderService.postOrder(user, orderRequest));
        return Pages.ORDER_FINALIZE;
    }
}
