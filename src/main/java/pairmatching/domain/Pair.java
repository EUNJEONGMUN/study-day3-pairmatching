package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pair {
    private final List<Crew> names = new ArrayList<>();

    private Pair(List<Crew> names) {
        this.names.addAll(names);
    }

    public static Pair of(Crew ... names) {
        return new Pair(Arrays.asList(names));
    }

    public void addCrew(Crew crew) {
        names.add(crew);
    }
}
