package com.demi.hdjassignment.controller.dto;

import com.demi.hdjassignment.entity.Hospital;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HospitalDto {

    @JsonProperty("hospital_id")
    private Long id;

    @JsonProperty("hospital_name")
    private String name;

    @JsonProperty("inst_id")
    private String instId;

    @JsonProperty("director")
    private String director;

    public HospitalDto(Hospital hospital) {
        id = hospital.getId();
        name = hospital.getName();
        instId = hospital.getInstId();
        director = hospital.getDirector();
    }
}
