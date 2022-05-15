package com.demi.hdjassignment.entity.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.InvalidParameterException;
import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Gender {

    MALE("M", "남"),
    FEMALE("F", "여");

    public String code;
    public String title;

    public static Gender findByCode(String code) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.hasCode(code))
                .findAny()
                .orElseThrow(
                        () -> new InvalidParameterException("Invalid Gender Code"))
                ;
    }

    public boolean hasCode(String code) {
        return code.equals(getCode());
    }

}