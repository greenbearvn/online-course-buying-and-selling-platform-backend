package com.courseplus.cartservice.controller;

import com.courseplus.cartservice.model.CartItem;
import com.courseplus.cartservice.service.inter.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/addCart")
    public boolean addCart(@RequestBody CartItem cartItem ) {
        return cartService.addCart(cartItem);
    }

    @GetMapping("/list")
    public Map getList() {
        return cartService.getCartItems();
    }

    @DeleteMapping("/delete/redis/{field}")
    public int deleteOneItem(@PathVariable String field ) {
        return cartService.deleteOneItem(field);
    }

    @DeleteMapping("/removeAll")
    public int removeAll( ) {
        return cartService.deleteCart();
    }

    @GetMapping("/moneyTotal")
    public Double totalMoney( ) {
        return cartService.calMoneyTotalInCart();
    }
}
