package com.javabackend.orderservice.service.inter;

import com.javabackend.orderservice.models.obj.CartItem;
import jakarta.mail.MessagingException;

import java.util.List;

public interface SendMailService {

    public void sendMail(List<CartItem> cartItems,String email) throws MessagingException;
}
