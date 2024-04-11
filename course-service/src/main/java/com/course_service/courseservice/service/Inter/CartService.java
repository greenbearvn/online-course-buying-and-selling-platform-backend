package com.course_service.courseservice.service.Inter;

import com.course_service.courseservice.models.req.CartItem;

import java.util.Map;

public interface CartService {

    public void addCart( CartItem cartItem);


    public Map getCartItems();

    public int deleteCart();

    public int deleteOneItem(String fieldName);

}
