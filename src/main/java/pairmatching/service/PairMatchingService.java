package pairmatching.service;

import pairmatching.domain.MissionGroup;
import pairmatching.repository.PairRepository;

public class PairMatchingService {
    private PairRepository pairRepository = new PairRepository();

    public boolean isExistsPair(MissionGroup missionGroup) {
        return !pairRepository.findByMissionGroup(missionGroup).isEmpty();
    }
}
