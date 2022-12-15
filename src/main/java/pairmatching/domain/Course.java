package pairmatching.domain;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Course {
    BACKEND("벡엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }

    private static final Map<String, Course> menus =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Course::getName, Function.identity())));

    public static Course from(String number) {
        return Optional.ofNullable(menus.get(number)).orElseThrow(
                () -> new IllegalArgumentException("올바른 과정을 입력해주세요."));
    }
}