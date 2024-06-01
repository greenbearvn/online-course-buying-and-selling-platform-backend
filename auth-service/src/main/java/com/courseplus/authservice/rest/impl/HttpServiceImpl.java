package com.courseplus.authservice.rest.impl;

import com.courseplus.authservice.rest.inter.HttpService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class HttpServiceImpl implements HttpService {
    @Override
    public UserDetailsService userDetailsService() {
        return null;
    }
}
