package com.mjc.school.service.validator;

import com.mjc.school.service.exceptions.ArgumentNotValidException;
import com.mjc.school.service.exceptions.ErrorCode;

public class Validator {

    private Validator() {}

    public static void validateNumber(Long number, String param) {
        if(number == null || number < 1) {
            throw new ArgumentNotValidException(String.format(
                    ErrorCode.NON_POSITIVE.getErrorMessage(), param, param, number));
        }
    }

    public static void validateString(String str, String param, int min, int max) {
        if(str == null) {
            throw new ArgumentNotValidException(String.format(
                    ErrorCode.NULL_CONTENT.getErrorMessage(), param));
        }
        if(str.length() < min || str.length() > max) {
            throw new ArgumentNotValidException(String.format(
                    ErrorCode.WRONG_LENGTH.getErrorMessage(), param, min, max, param, str));
        }
    }
}
