package com.demi.hdjassignment.entity.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.InvalidParameterException;
import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum VisitStatus {

    VISITING("1", "방문중"),
    COMPLETE("2", "종료"),
    CANCEL("3", "취소");

    public String code;
    public String title;

    public static VisitStatus findByCode(String code) {
        return Arrays.stream(VisitStatus.values())
                .filter(status -> status.hasCode(code))
                .findAny()
                .orElseThrow(
                        () -> new InvalidParameterException("Invalid VisitStatus Code"))
                ;
    }

    public boolean hasCode(String code) {
        return code.equals(getCode());
    }

}