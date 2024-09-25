package pairmatching.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

    @DisplayName("백엔드 Course 생성 테스트")
    @Test
    void createBackendCourse() {
        Course backend = Course.from("백엔드");
        assertThat(backend).isEqualTo(Course.BACKEND);
    }

    @DisplayName("프론트엔드 Course 생성 테스트")
    @Test
    void createFrontendCourse() {
        Course frontend = Course.from("프론트엔드");
        assertThat(frontend).isEqualTo(Course.FRONTEND);
    }
}