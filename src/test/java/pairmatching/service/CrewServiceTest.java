package pairmatching.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pairmatching.domain.Course;
import pairmatching.repository.CrewRepository;

import static org.assertj.core.api.Assertions.assertThat;

class CrewServiceTest {
    private CrewService crewService = new CrewService();
    private CrewRepository crewRepository = new CrewRepository();

    @DisplayName("크루들의 이름을 불러온다.")
    @Test
    void readCrewTest() {
        crewService.initCrews();
        assertThat(crewRepository.findByCourse(Course.BACKEND).size()).isEqualTo(20);
        assertThat(crewRepository.findByCourse(Course.FRONTEND).size()).isEqualTo(15);
    }
}