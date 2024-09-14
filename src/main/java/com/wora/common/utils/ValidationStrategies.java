package com.wora.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ValidationStrategies {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\(?(\\d{3})\\)?[-.\\s]?(\\d{3})[-.\\s]?(\\d{4})$");

    public static final Predicate<String> NOT_BLANK = s -> !s.isEmpty();
    public static final Predicate<Integer> POSITIVE_INT = i -> i > 0;
    public static final Predicate<Double> POSITIVE_DOUBLE = i -> i > 0;
    public static final Predicate<Float> POSITIVE_FLOAT = i -> i > 0;
    public static final Predicate<LocalDate> FUTURE_DATE = d -> d.isAfter(LocalDate.now());
    public static final Predicate<LocalDateTime> FUTURE_DATE_TIME = d -> d.isAfter(LocalDateTime.now());
    public static final Predicate<String> VALID_EMAIL = email -> EMAIL_PATTERN.matcher(email).matches();
    public static final Predicate<String> VALID_PHONE = phone -> PHONE_PATTERN.matcher(phone).matches();

    @SafeVarargs
    public static <T> Predicate<T> combine(Predicate<T>... strategies) {
        return input -> {
            for (Predicate<T> strategy : strategies) {
                if (!strategy.test(input)) return false;
            }
            return true;
        };
    }
}
