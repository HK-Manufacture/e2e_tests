package io.testomat.e2e_tests.utils;

import org.jetbrains.annotations.NotNull;

public class StringParsers {

    @NotNull
    public static Integer parseIntegerFromString(String countOfTests) {
        String text = countOfTests.replaceAll("\\D+", "");
        return Integer.parseInt(text);
    }

}
