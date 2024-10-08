package com.wora.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;


public class InputScanner {
    private final static Scanner SCANNER = new Scanner(System.in);

    private static <T> T scanWithValidation(String prompt, InputParser<T> parser, Predicate<T> validator) {
        while (true) {
            System.out.print(prompt);
            final String input = SCANNER.nextLine().trim();

            try {
                T result = parser.parse(input);
                if (validator.test(result)) return result;
                else System.out.println("invalid input please try again !");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Invalid input format. Please try again.");
            }
        }
    }

    public static <T> T executeIfUserConfirmsWithResult(String prompt, Supplier<T> action, T defaultValue) {
        final Boolean userConfirmed = scanBoolean(prompt + "(y/n): ");
        return userConfirmed ? action.get() : defaultValue;
    }

    public static void executeIfUserConfirms(String prompt, Runnable action) {
        final Boolean userConfirmed = scanBoolean(prompt + "(y/n): ");
        if (userConfirmed) action.run();
    }

    public static Integer scanInt(String prompt, Predicate<Integer> validator) {
        return scanWithValidation(prompt, Integer::parseInt, validator);
    }

    public static Double scanDouble(String prompt, Predicate<Double> validator) {
        return scanWithValidation(prompt, Double::parseDouble, validator);
    }

    public static Float scanFloat(String prompt, Predicate<Float> validator) {
        return scanWithValidation(prompt, Float::parseFloat, validator);
    }

    public static String scanString(String prompt, Predicate<String> validator) {
        return scanWithValidation(prompt, String::toString, validator);
    }

    public static LocalDate scanDate(String prompt, Predicate<LocalDate> validator) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return scanWithValidation(prompt, input -> LocalDate.parse(input, formatter), validator);
    }

    public static LocalDateTime scanDateTime(String prompt, Predicate<LocalDateTime> validator) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return scanWithValidation(prompt, input -> LocalDateTime.parse(input, formatter), validator);
    }

    public static UUID scanUuid(String prompt, Predicate<UUID> validator) {
        return scanWithValidation(prompt, UUID::fromString, validator);
    }

    public static <E extends Enum<E>> E scanEnum(String prompt, Class<E> enumClass) {
        final E[] enumConstants = enumClass.getEnumConstants();
        final String enumOptions = IntStream.range(0, enumConstants.length)
                .mapToObj(i -> String.format("%d. %s", i + 1, enumConstants[i].name()))
                .reduce((a, b) -> a + "\n" + b)
                .orElse("");

        String fullPrompt = prompt + "\n" + enumOptions + "\nEnter the number of your choice: ";

        return scanWithValidation(
                fullPrompt,
                input -> {
                    int index = Integer.parseInt(input) - 1;
                    if (index < 0 || index >= enumConstants.length) {
                        throw new IllegalArgumentException("Invalid index");
                    }
                    return enumConstants[index];
                },
                (Predicate<E>) ValidationStrategies.VALID_ENUM
        );
    }

    public static Boolean scanBoolean(String prompt) {
        return scanWithValidation(prompt,
                input -> switch (input.toLowerCase()) {
                    case "true", "yes", "y", "1" -> true;
                    case "false", "no", "n", "0" -> false;
                    default -> throw new IllegalArgumentException("Invalid boolean input");
                },
                ValidationStrategies.VALID_BOOLEAN
        );
    }

    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    @FunctionalInterface
    interface InputParser<T> {
        T parse(String input) throws Exception;
    }
}
