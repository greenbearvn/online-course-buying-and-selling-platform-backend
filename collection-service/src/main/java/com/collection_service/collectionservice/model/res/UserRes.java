package com.collection_service.collectionservice.model.res;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRes {

    @JsonProperty("userId")
    private int userId;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty( "email")
    private String email;

    @JsonProperty("role")
    private Role role;
}
