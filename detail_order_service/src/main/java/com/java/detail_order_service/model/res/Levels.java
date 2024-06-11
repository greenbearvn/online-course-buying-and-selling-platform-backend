package com.java.detail_order_service.model.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Levels {
    @JsonProperty(value = "idLevels")
    private int idLevels;

    @JsonProperty(value = "nameLevel")
    private String nameLevel;
}
