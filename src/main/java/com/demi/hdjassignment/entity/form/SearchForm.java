package com.demi.hdjassignment.entity.form;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SearchForm {

    private Long hospitalId;
    private String name;
    private String regId;
    private String birth;
    private Integer pageSize;
    private Integer pageNo;

}
