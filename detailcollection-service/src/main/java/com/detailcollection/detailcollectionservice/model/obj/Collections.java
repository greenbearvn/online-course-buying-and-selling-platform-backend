package com.detailcollection.detailcollectionservice.model.obj;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Collections {

    private int collectionId;
    private int userId;
}
