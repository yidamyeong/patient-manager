package com.demi.hdjassignment.controller.dto;

import com.demi.hdjassignment.entity.Patient;
import com.demi.hdjassignment.entity.Visit;
import com.demi.hdjassignment.entity.code.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;

@Data
@AllArgsConstructor
public class PatientListDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("reg_id")
    private String regId;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("birth")
    private String birth;

    @JsonProperty("mobile")
    private String mobile;

    @JsonProperty("recent_visit")
    private String recentVisit;

    public PatientListDto(Patient patient) {
        name = patient.getName();
        regId = patient.getRegId();
        gender = patient.getGender();
        birth = patient.getBirth();
        mobile = patient.getMobile();
        recentVisit = patient.getVisits().stream()
                .sorted(Comparator.comparing(Visit::getCreatedAt).reversed())
                .map(visit -> visit.getCreatedAt().toString())
                .findFirst()
                .orElse("");
    }

}
