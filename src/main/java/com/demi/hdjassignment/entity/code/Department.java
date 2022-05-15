package com.demi.hdjassignment.entity.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.InvalidParameterException;
import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Department {

    INTERNAL_MEDICINE("01", "내과"),
    OPHTHALMOLOGY("02", "안과");

    public String code;
    public String title;

    public static Department findByCode(String code) {
        return Arrays.stream(Department.values())
                .filter(dept -> dept.hasCode(code))
                .findAny()
                .orElseThrow(
                        () -> new InvalidParameterException("Invalid Department Code"))
                ;
    }

    public boolean hasCode(String code) {
        return code.equals(getCode());
    }

}