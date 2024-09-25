package pairmatching.repository;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CrewRepository {
    private static List<Crew> crews = new ArrayList<>();

    public void addCrew(Crew crew) {
        this.crews.add(crew);
    }

    public List<String> findByCourse(Course course) {
        return crews.stream()
                .filter(crew -> crew.getCourse() == course)
                .map(Crew::getName)
                .collect(Collectors.toList());
    }
}