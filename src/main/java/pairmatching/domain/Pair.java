package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Pair {
    private final List<Crew> names = new ArrayList<>();

    private Pair(List<Crew> names) {
        this.names.addAll(names);
    }

    public static Pair of(Crew... names) {
        return new Pair(Arrays.asList(names));
    }

    public void addCrew(Crew crew) {
        names.add(crew);
    }

    public boolean isSamePair(Pair pair) {
        return pair.names.stream()
                .filter(name -> this.names.contains(name))
                .count() >= 2;
    }

    public List<String> getNames() {
        return names.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());
    }
}
