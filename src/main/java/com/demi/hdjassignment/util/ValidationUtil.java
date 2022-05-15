package com.demi.hdjassignment.util;

import java.security.InvalidParameterException;
import java.util.Objects;

public class ValidationUtil {

    public static void rejectIfNotEqual(Object obj1, Object obj2, String subject) {
        if (!Objects.equals(obj1, obj2)) {
            throw new InvalidParameterException("Not matched " + subject);
        }
    }

}
