package com.collection_service.collectionservice.model.req;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionReq {
    @JsonProperty("collectionId")
    private int collectionId;

    @JsonProperty( "userId")
    private String userId;
}
