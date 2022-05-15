package com.demi.hdjassignment.entity.form;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PatientCreateForm {

    @NotNull
    private Long hospitalId;

    @NotBlank
    private String name;

    @NotBlank
    private String gender;

    @NotBlank
    private String birth;

    @NotBlank
    private String mobile;

}
