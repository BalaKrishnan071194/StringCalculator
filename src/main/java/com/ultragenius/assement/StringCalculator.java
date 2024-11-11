package com.ultragenius.assement;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator implements Calculator {

    public int add(String numbers)  {
        String delimiter = ",|\n";
        String negativeNumbersStr = "";
        String splitNumbers[];

        ArrayList<Integer> negativeNumbers = new ArrayList<>();
        numbers = numbers.replaceAll(" ", "");

        if (numbers.isEmpty()) return 0;

        int startIndex = numbers.indexOf("//");
        int endIndex = numbers.indexOf("\\n");


        if (startIndex != -1) {
            delimiter = numbers.substring(startIndex + 2, endIndex);
            numbers = numbers.substring(endIndex + 2);

            if (delimiter.endsWith("]") && delimiter.indexOf("[") == 0) {
                Pattern pattern = Pattern.compile("\\[(.*?)\\]");
                Matcher matcher = pattern.matcher(delimiter);

                int groupCount = matcher.groupCount();
                while (matcher.find()) {
                    for (int i = 0; i <= groupCount; i++) {
                        String m = matcher.group(i);
                        numbers = numbers.replaceAll(Pattern.quote(m), ",");
                    }
                }
                delimiter = ",";
            }


            splitNumbers = numbers.split(Pattern.quote(delimiter));
        } else
            splitNumbers = numbers.split(delimiter);


        int total = 0;
        for (String n : splitNumbers) {
            Integer current = Integer.parseInt(n);
            if (current < 0) {
                negativeNumbers.add(current);
                negativeNumbersStr += (String.format("%s ", current));
            }
            if (current < 1001) total += current;
        }
        NegativesNotAllowedException.throwExceptionIfNegativeExists(negativeNumbersStr);
        return total;
    }
}


