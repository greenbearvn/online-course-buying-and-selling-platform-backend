package com.video_service.videoservice.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {

    private final String CLOUD_NAME = "dudqedtrs";
    private final String API_KEY = "935896145724342";
    private final String API_SECRET = "f7RQxpHSn1sADt4stOfMoXtJB5g";
    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name",CLOUD_NAME);
        config.put("api_key",API_KEY);
        config.put("api_secret",API_SECRET);

        return   new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dudqedtrs",
                "api_key", "935896145724342",
                "api_secret", API_SECRET,
                "secure", true));
    }
}
