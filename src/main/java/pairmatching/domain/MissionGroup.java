package pairmatching.domain;

import java.util.List;
import java.util.Objects;

public class MissionGroup {
    private Course course;
    private Mission mission;

    private MissionGroup(Course course, Mission mission) {
        this.course = course;
        this.mission = mission;
    }

    public static MissionGroup from(List<String> input) {
        return new MissionGroup(Course.from(input.get(0)),
                Mission.of(Level.from(input.get(1)), input.get(2)));
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, mission);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MissionGroup)) {
            return false;
        }
        if (((MissionGroup) obj).course.equals(course)
                && ((MissionGroup) obj).mission.equals(mission)) {
            return true;
        }
        return false;
    }
}