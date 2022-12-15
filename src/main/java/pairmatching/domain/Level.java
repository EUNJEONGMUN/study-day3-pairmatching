package pairmatching.domain;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private String name;

    Level(String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }

    private static final Map<String, Level> menus =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Level::getName, Function.identity())));

    public static Level from(String number) {
        return Optional.ofNullable(menus.get(number)).orElseThrow(
                () -> new IllegalArgumentException("올바른 레벨을 입력해주세요."));
    }
}