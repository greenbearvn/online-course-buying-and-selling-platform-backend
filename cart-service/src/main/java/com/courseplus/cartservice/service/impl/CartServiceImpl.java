package com.courseplus.cartservice.service.impl;

import com.courseplus.cartservice.model.CartItem;
import com.courseplus.cartservice.repository.inter.BaseRedisRepository;
import com.courseplus.cartservice.service.inter.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final BaseRedisRepository baseRedisRepository;

    private final String KEY = "new_cart";
    @Override
    public boolean addCart(CartItem cartItem) {
        try{
            if(baseRedisRepository.isMapEmpty(KEY)) {
                Map<String,CartItem> map = new HashMap<String,CartItem>();
                map.put( "id"+cartItem.getCourseId(), cartItem);
                baseRedisRepository.saveListData(KEY,map);
            }

            if(!baseRedisRepository.isMapEmpty(KEY)){
                baseRedisRepository.addValueToMap(KEY,"id"+cartItem.getCourseId(),cartItem);
            }

            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    @Override
    public Map getCartItems() {
        return baseRedisRepository.getMapData(KEY);
    }

    @Override
    public int deleteCart() {
        return baseRedisRepository.removeAllValuesFromMap(KEY);
    }

    @Override
    public int deleteOneItem(String fieldName) {
        return baseRedisRepository.removeValueFromMap(KEY,fieldName);
    }

    @Override
    public Double calMoneyTotalInCart() {
        double totalMoney = 0.0;

        Map<String,CartItem> cartItem = baseRedisRepository.getMapData(KEY);

        if (cartItem != null) {
            for (CartItem item : cartItem.values()) {
                totalMoney += item.getNewPrice();
            }
        }

        return totalMoney;
    }
}
