package com.mjc.school.controller.utils;

import com.mjc.school.service.exceptions.ArgumentNotValidException;
import com.mjc.school.service.exceptions.ErrorCode;

import java.util.InputMismatchException;
import java.util.Scanner;

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
}
