package pairmatching.view;

import pairmatching.dto.PairsDto;

import java.util.List;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printResult(PairsDto pairs) {
        List<List<String>> pairsNames = pairs.getPairNames();
        for (List<String> names : pairsNames) {
            System.out.println(String.join(" : ", names));
        }
    }
}