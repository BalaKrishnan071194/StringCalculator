package com.ultragenius.assement;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NegativesNotAllowedException extends RuntimeException {
    public NegativesNotAllowedException(String s) {
        super(s);
    }

    public static void throwExceptionIfNegativeExists(String negativeNumbersStr) {
        if (!negativeNumbersStr.isEmpty()) {
            throw new NegativesNotAllowedException("Negatives Not Allowed. Numbers are: " + negativeNumbersStr);
        }
    }
}
