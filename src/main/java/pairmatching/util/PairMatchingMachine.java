package pairmatching.util;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Pair;

import java.util.ArrayList;
import java.util.List;

public class PairMatchingMachine {

    public static List<String> shuffleCrews(List<String> crews) {
        return Randoms.shuffle(crews);
    }

    public static List<Pair> makePair(Course course, List<String> names) {
        List<Pair> result = new ArrayList<>();
        for (int i = 0; i < (names.size() / 2) * 2; i += 2) {
            result.add(Pair.of(Crew.of(course, names.get(i)), Crew.of(course, names.get(i + 1))));
        }
        if (names.size() % 2 != 0) {
            result.get(result.size() - 1)
                    .addCrew(Crew.of(course, names.get(names.size() - 1)));
        }
        return result;
    }

    public static boolean isDuplicated(List<Pair> sameMissionPairs, List<Pair> pairs) {
        return pairs.stream()
                .anyMatch(pair -> sameMissionPairs.stream()
                        .anyMatch(sameMissionPair -> pair.isSamePair(sameMissionPair)));
    }
}