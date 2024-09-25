package pairmatching.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Pair;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PairMatchingMachineTest {

    @Test
    void shuffleCrews() {
        List<String> names = List.of("철수", "짱구", "맹구", "유리", "훈이");
        List<String> result = PairMatchingMachine.shuffleCrews(names);
        Assertions.assertThat(result).containsOnly("철수", "짱구", "맹구", "유리", "훈이");
    }

    @Test
    void makePair() {
        List<String> names = List.of("철수", "짱구", "맹구", "유리", "훈이");
        List<Pair> result = PairMatchingMachine.makePair(Course.BACKEND, names);
        Assertions.assertThat(result.get(0).getNames()).isEqualTo(List.of("철수", "짱구"));
        Assertions.assertThat(result.get(1).getNames()).isEqualTo(List.of("맹구", "유리", "훈이"));
    }

    @Test
    void isDuplicated() {
        Crew 철수 = Crew.of(Course.BACKEND, "철수");
        Crew 짱구 = Crew.of(Course.BACKEND, "짱구");
        Crew 맹구 = Crew.of(Course.BACKEND, "맹구");
        Crew 유리 = Crew.of(Course.BACKEND, "유리");
        Crew 훈이 = Crew.of(Course.BACKEND, "훈이");
        List<Pair> sameLevelPairs = List.of(Pair.of(철수, 짱구), Pair.of(맹구, 유리, 훈이));
        List<Pair> randomPairs = List.of(Pair.of(철수, 맹구), Pair.of(짱구, 유리, 훈이));
        Assertions.assertThat(PairMatchingMachine.isDuplicated(sameLevelPairs, randomPairs)).isTrue();
    }

    @Test
    void isNotDuplicated() {
        Crew 철수 = Crew.of(Course.BACKEND, "철수");
        Crew 짱구 = Crew.of(Course.BACKEND, "짱구");
        Crew 맹구 = Crew.of(Course.BACKEND, "맹구");
        Crew 유리 = Crew.of(Course.BACKEND, "유리");
        Crew 훈이 = Crew.of(Course.BACKEND, "훈이");
        List<Pair> sameLevelPairs = List.of(Pair.of(철수, 짱구), Pair.of(맹구, 유리, 훈이));
        List<Pair> randomPairs = List.of(Pair.of(철수, 맹구), Pair.of(짱구, 유리));
        Assertions.assertThat(PairMatchingMachine.isDuplicated(sameLevelPairs, randomPairs)).isFalse();
    }
}