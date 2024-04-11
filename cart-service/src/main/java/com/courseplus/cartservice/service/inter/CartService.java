package com.courseplus.cartservice.service.inter;

import com.courseplus.cartservice.model.CartItem;

import java.util.Map;

public interface CartService {
    public void addCart( CartItem cartItem);


    public Map getCartItems();

    public int deleteCart();

    public int deleteOneItem(String fieldName);

    public Double calMoneyTotalInCart();

}
