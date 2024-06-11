package com.detailcollection.detailcollectionservice.model.obj;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lessions {

    private int lessionId;


    private String lessionName;

    private String lessionDuration;

    private int courseId;
}
