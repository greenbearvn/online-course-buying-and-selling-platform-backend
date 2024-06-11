package com.detailcollection.detailcollectionservice.model.obj;

import com.detailcollection.detailcollectionservice.entity.DetailCollection;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailCollectionRes {

    private DetailCollection detailCollection;

    private CourseRes courseRes;

    public  static  DetailCollectionRes detailCollectionBuilder(DetailCollection detailCollection, CourseRes courseRes){
        return  DetailCollectionRes.builder()
                .detailCollection(detailCollection)
                .courseRes(courseRes)
                .build();
    }
}
