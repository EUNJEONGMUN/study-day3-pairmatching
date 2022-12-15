package pairmatching.repository;

import pairmatching.domain.MissionGroup;
import pairmatching.domain.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class PairRepository {
    private static Map<MissionGroup, List<Pair>> pairs = new HashMap<>();

    public List<Pair> findByMissionGroup(MissionGroup missionGroup) {
        return Optional.ofNullable(pairs.get(missionGroup))
                .orElse(Collections.emptyList());
    }

    public List<Pair> findByMissionLevel(MissionGroup missionGroup) {
        return pairs.keySet().stream()
                .filter(group -> group.isSameCourseAndLevel(missionGroup))
                .map(pairs::get)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public void addPairs(MissionGroup missionGroup, List<Pair> pairs) {
        this.pairs.put(missionGroup, pairs);
    }
}
