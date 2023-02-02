package com.mjc.school.controller.utils;

import com.mjc.school.service.exceptions.ArgumentNotValidException;
import com.mjc.school.service.exceptions.ErrorCode;

import java.util.*;
import java.util.stream.Collectors;

public class ScannerUtils {

    private ScannerUtils() {}

    public static Long getNumberFromScanner(String param, Scanner sc) {
        try {
            Long id = sc.nextLong();
            sc.nextLine();
            return id;
        } catch (InputMismatchException e) {
            sc.nextLine();
            throw new ArgumentNotValidException(String.format(
                    ErrorCode.NOT_NUMBER.getErrorMessage(), param));
        }
    }

    public static Set<Long> getSetNumberFromScanner(String param, Scanner sc) {
        try {
            Set<Long> ids = new HashSet<>();
            String str = sc.nextLine();
            if(!("".equals(str))) {
                String[] idStr = str.split(",");
                ids = Arrays.stream(idStr).map(Long::parseLong).collect(Collectors.toSet());
            }
            return ids;
        } catch (InputMismatchException e) {
            sc.nextLine();
            throw new ArgumentNotValidException(String.format(
                    ErrorCode.NOT_NUMBER.getErrorMessage(), param));
        }
    }

    public static Set<String> getSetStringFromScanner(String param, Scanner sc) {
        try {
            Set<String> stringSet = new HashSet<>();
            String str = sc.nextLine();
            if(!("".equals(str))) {
                String[] params = str.split(",");
                stringSet = Arrays.stream(params).collect(Collectors.toSet());
            }
            return stringSet;
        } catch (InputMismatchException e) {
            sc.nextLine();
            throw new ArgumentNotValidException(String.format(
                    ErrorCode.NOT_NUMBER.getErrorMessage(), param));
        }
    }
}
