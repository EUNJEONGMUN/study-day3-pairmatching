package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    private static final String DELIMITER = ",";

    public static String readMenu() {
        System.out.println("기능을 선택하세요.\n" +
                "1. 페어 매칭\n" +
                "2. 페어 조회\n" +
                "3. 페어 초기화\n" +
                "Q. 종료");
        return Console.readLine();
    }

    public static List<String> readCourseAndMission() {
        System.out.println("과정, 레벨, 미션을 선택하세요.\n" +
                "ex) 백엔드, 레벨1, 자동차경주");
        String input = Console.readLine();
        validateInputFormat(input);
        return Arrays.stream(input.split(DELIMITER))
                .map(s -> s.replaceAll(" ", ""))
                .collect(Collectors.toList());
    }

    private static void validateInputFormat(String input) {
        if (input.split(DELIMITER, -1).length != 3) {
            throw new IllegalArgumentException("과정, 레벨, 미션은 쉼표로 구분하어 입력해야 합니다.");
        }
    }
}