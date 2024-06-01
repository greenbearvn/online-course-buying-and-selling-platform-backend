package com.courseplus.authservice.rest.inter;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface HttpService {
    UserDetailsService userDetailsService();
}
