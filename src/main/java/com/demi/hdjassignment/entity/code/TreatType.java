package com.demi.hdjassignment.entity.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.InvalidParameterException;
import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TreatType {

    PRESCRIPTION("D", "약처방"),
    TEST("T", "검사");

    public String code;
    public String title;

    public static TreatType findByCode(String code) {
        return Arrays.stream(TreatType.values())
                .filter(type -> type.hasCode(code))
                .findAny()
                .orElseThrow(
                        () -> new InvalidParameterException("Invalid TreatType Code"))
        ;
    }

    public boolean hasCode(String code) {
        return code.equals(getCode());
    }

}