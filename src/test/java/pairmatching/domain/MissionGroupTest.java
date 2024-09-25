package pairmatching.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MissionGroupTest {

    @DisplayName("MissionGroup 생성 테스트")
    @Test
    void createMissionGroup() {
        MissionGroup lotto = MissionGroup.from(List.of("백엔드", "레벨1", "로또"));
        assertThat(lotto).extracting("course").isEqualTo(Course.BACKEND);
        assertThat(lotto).extracting("mission").isEqualTo(Mission.LOTTO);
    }

    @DisplayName("같은 코스, 같은 레벨 테스트")
    @Test
    void isSameCourseAndLevel() {
        MissionGroup lotto = MissionGroup.from(List.of("백엔드", "레벨1", "로또"));
        MissionGroup racingCar = MissionGroup.from(List.of("백엔드", "레벨1", "자동차경주"));
        assertThat(lotto.isSameCourseAndLevel(racingCar)).isTrue();

    }

    @DisplayName("같은 코스, 다른 레벨 테스트")
    @Test
    void isSameCourseAndLevel2() {
        MissionGroup payment = MissionGroup.from(List.of("백엔드", "레벨2", "결제"));
        MissionGroup racingCar = MissionGroup.from(List.of("백엔드", "레벨1", "자동차경주"));
        assertThat(payment.isSameCourseAndLevel(racingCar)).isFalse();
    }
}