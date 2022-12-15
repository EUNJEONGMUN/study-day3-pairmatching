package pairmatching.domain;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Command {
    YES("네"),
    NO("아니오");

    private String name;

    Command(String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }

    private static final Map<String, Command> commands =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Command::getName, Function.identity())));

    public static Command from(String input) {
        return Optional.ofNullable(commands.get(input)).orElseThrow(
                () -> new IllegalArgumentException("네 | 아니오를 선택해야 합니다."));
    }
}