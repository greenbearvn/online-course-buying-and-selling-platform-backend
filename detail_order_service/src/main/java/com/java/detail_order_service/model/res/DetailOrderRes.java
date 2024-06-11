package com.java.detail_order_service.model.res;


import com.java.detail_order_service.entity.DetailOrder;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailOrderRes {

    private DetailOrder detailOrder;

    private CourseRes courseRes;
    public  static  DetailOrderRes detailOrderRes(DetailOrder detailOrder, CourseRes courseRes){
        return  DetailOrderRes.builder()
                .detailOrder(detailOrder)
                .courseRes(courseRes)
                .build();
    }
}
