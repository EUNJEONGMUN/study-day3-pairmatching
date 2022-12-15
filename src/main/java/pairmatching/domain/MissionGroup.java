package pairmatching.domain;

import java.util.List;

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
}