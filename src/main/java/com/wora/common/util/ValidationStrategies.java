package com.wora.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ValidationStrategies {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(?:\\+212[\\s]?|0)([5-7]\\d{8})$");
    private final static Pattern UUID_REGEX = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    public static final Predicate<String> NOT_BLANK = s -> !s.isEmpty();
    public static final Predicate<Integer> POSITIVE_INT = i -> i >= 0;
    public static final Predicate<Double> POSITIVE_DOUBLE = i -> i > 0;
    public static final Predicate<Float> POSITIVE_FLOAT = i -> i > 0;
    public static final Predicate<LocalDate> FUTURE_DATE = d -> d.isAfter(LocalDate.now());
    public static final Predicate<LocalDateTime> FUTURE_DATE_TIME = d -> d.isAfter(LocalDateTime.now());
    public static final Predicate<Boolean> VALID_BOOLEAN = Objects::nonNull;
    public static final Predicate<String> VALID_EMAIL = email -> EMAIL_PATTERN.matcher(email).matches();
    public static final Predicate<String> VALID_PHONE = phone -> PHONE_PATTERN.matcher(phone).matches();
    public static final Predicate<UUID> VALID_UUID = uuid -> UUID_REGEX.matcher(uuid.toString()).matches();
    public static final Predicate<Enum<?>> VALID_ENUM = Objects::nonNull;

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
