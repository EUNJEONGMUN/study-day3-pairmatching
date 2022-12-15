package pairmatching.service;

import pairmatching.domain.MissionGroup;
import pairmatching.domain.Pair;
import pairmatching.repository.PairRepository;

import java.util.List;

public class PairMatchingService {
    private PairRepository pairRepository = new PairRepository();
    private CrewService crewService = new CrewService();
    public boolean isExistsPair(MissionGroup missionGroup) {
        return !pairRepository.findByMissionGroup(missionGroup).isEmpty();
    }

    public List<Pair> matchPair(MissionGroup missionGroup) {
        List<String> crews = crewService.getCrewsByCourse(missionGroup.getCourse());
    }
}
