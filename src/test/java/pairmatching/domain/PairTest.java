package pairmatching.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PairTest {
    @Test
    void isSamePairTest() {
        Pair pair1 = Pair.of(Crew.of(Course.BACKEND, "철수"), Crew.of(Course.BACKEND, "영희"));
        Pair pair2 = Pair.of(Crew.of(Course.BACKEND, "영희"), Crew.of(Course.BACKEND, "철수"));
        Assertions.assertThat(pair1.isSamePair(pair2)).isTrue();
    }

    @Test
    void isSamePairTest2() {
        Pair pair1 = Pair.of(Crew.of(Course.BACKEND, "철수"), Crew.of(Course.BACKEND, "영희"), Crew.of(Course.BACKEND, "유리"));
        Pair pair2 = Pair.of(Crew.of(Course.BACKEND, "영희"), Crew.of(Course.BACKEND, "철수"));
        Assertions.assertThat(pair1.isSamePair(pair2)).isTrue();
    }

}