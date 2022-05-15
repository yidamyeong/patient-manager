package com.demi.hdjassignment.controller.dto;

import com.demi.hdjassignment.entity.Patient;
import com.demi.hdjassignment.entity.code.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {

    @JsonProperty("patient_id")
    private Long id;

    @JsonProperty("hospital")
    private HospitalDto hospital;

    @JsonProperty("patient_name")
    private String name;

    @JsonProperty("reg_id")
    private String regId;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("birth")
    private String birth;

    @JsonProperty("mobile")
    private String mobile;

    @JsonProperty("visits")
    private List<VisitDto> visits;

    public PatientDto(Patient patient) {
        id = patient.getId();
        hospital = new HospitalDto(patient.getHospital());
        name = patient.getName();
        regId = patient.getRegId();
        gender = patient.getGender();
        birth = patient.getBirth();
        mobile = patient.getMobile();
        visits = patient.getVisits().stream()
                .map(VisitDto::new)
                .collect(Collectors.toList());
    }

}
