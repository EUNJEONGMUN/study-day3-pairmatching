package pairmatching.repository;

import pairmatching.domain.MissionGroup;
import pairmatching.domain.Pair;

import java.util.*;

public class PairRepository {
    private static Map<MissionGroup, List<Pair>> pairs = new HashMap<>();

    public List<Pair> findByMissionGroup(MissionGroup missionGroup) {
        return Optional.ofNullable(pairs.get(missionGroup))
                .orElse(Collections.emptyList());
    }
}
