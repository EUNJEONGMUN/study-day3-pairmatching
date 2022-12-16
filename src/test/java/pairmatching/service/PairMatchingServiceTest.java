package pairmatching.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pairmatching.domain.MissionGroup;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PairMatchingServiceTest {
    private CrewService crewService = new CrewService();
    private PairMatchingService pairMatchingService = new PairMatchingService();

    @BeforeEach
    void setUp() {
        crewService.initCrews();
    }

    @Test
    void createNotExistsMissionGroup() {
        MissionGroup lotto = MissionGroup.from(List.of("백엔드", "레벨1", "로또"));
        assertThat(pairMatchingService.isExistsPair(lotto)).isFalse();
    }

    @Test
    void createExistsMissionGroup() {
        MissionGroup lotto = MissionGroup.from(List.of("백엔드", "레벨1", "로또"));
        pairMatchingService.createNotDuplicatePair(lotto);
        assertThat(pairMatchingService.isExistsPair(lotto)).isTrue();
    }

    @Test
    void getPairs() {
        MissionGroup lotto = MissionGroup.from(List.of("백엔드", "레벨1", "로또"));
        assertThatThrownBy(() -> pairMatchingService.getPairs(lotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("매칭 이력이 없습니다.");
    }

    @AfterEach
    void end() {
        pairMatchingService.initMatchingHistory();
    }
}