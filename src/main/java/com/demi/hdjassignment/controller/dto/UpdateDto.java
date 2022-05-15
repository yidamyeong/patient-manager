package com.demi.hdjassignment.controller.dto;

import com.demi.hdjassignment.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateDto {

    private Long id;
    private String name;
    private String birth;
    private String mobile;

    public UpdateDto(Patient patient) {
        id = patient.getId();
        name = patient.getName();
        birth = patient.getBirth();
        mobile = patient.getMobile();
    }
}
