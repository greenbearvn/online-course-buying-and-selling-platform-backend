package com.javabackend.orderservice.models.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionRes {
    @JsonProperty("collectionId")
    private int collectionId;

    @JsonProperty( "userId")
    private String userId;
}
