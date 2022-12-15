package pairmatching.repository;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CrewRepository {
    private static List<Crew> crews = new ArrayList<>();

    public void addCrew(Crew crew) {
        this.crews.add(crew);
    }

    public List<String> readBackendCrews() {
        return crews.stream()
                .filter(crew -> crew.getCourse() == Course.BACKEND)
                .map(Crew::getName)
                .collect(Collectors.toList());
    }

    public List<String> readFrontendCrews() {
        return crews.stream()
                .filter(crew -> crew.getCourse() == Course.FRONTEND)
                .map(Crew::getName)
                .collect(Collectors.toList());
    }
}