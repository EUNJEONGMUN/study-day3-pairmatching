package pairmatching.domain;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Menu {
    PAIR_MATCHING("1"),
    LOOKUP_PAIR("2"),
    INIT_PAIR("3"),
    QUIT("Q");

    private final String code;

    Menu(String code) {
        this.code = code;
    }

    private String getCode() {
        return code;
    }

    private static final Map<String, Menu> menus =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Menu::getCode, Function.identity())));

    public static Menu from(String number) {
        return Optional.ofNullable(menus.get(number)).orElseThrow(
                () -> new IllegalArgumentException("선택할 수 없는 기능입니다."));
    }
}