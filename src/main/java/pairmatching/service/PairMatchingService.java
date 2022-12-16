package pairmatching.service;

import pairmatching.domain.MissionGroup;
import pairmatching.domain.Pair;
import pairmatching.dto.PairsDto;
import pairmatching.repository.PairRepository;
import pairmatching.util.PairMatchingMachine;

import java.util.List;

public class PairMatchingService {
    private PairRepository pairRepository = new PairRepository();
    private CrewService crewService = new CrewService();

    public boolean isExistsPair(MissionGroup missionGroup) {
        return !pairRepository.findByMissionGroup(missionGroup).isEmpty();
    }

    public void createNotDuplicatePair(MissionGroup missionGroup) {
        for (int i = 0; i < 3; i++) {
            List<Pair> pairs = matchPair(missionGroup);
            List<Pair> sameMissionPairs = pairRepository.findByMissionLevel(missionGroup);
            if (!PairMatchingMachine.isDuplicated(sameMissionPairs, pairs)) {
                addPairs(missionGroup, pairs);
                return;
            }
        }
        throw new IllegalStateException("페어매칭을 할 수 없습니다.");
    }

    public PairsDto getPairs(MissionGroup missionGroup) {
        List<Pair> pairs = pairRepository.findByMissionGroup(missionGroup);
        if (pairs.isEmpty()) {
            throw new IllegalArgumentException("매칭 이력이 없습니다.");
        }
        return PairsDto.of(pairs);
    }

    private List<Pair> matchPair(MissionGroup missionGroup) {
        List<String> crews = crewService.getCrewsByCourse(missionGroup.getCourse());
        if (crews.size() < 2) {
            throw new IllegalArgumentException("크루가 2명 이상일 때 페어를 맺을 수 있습니다.");
        }
        List<String> shuffleCrews = PairMatchingMachine.shuffleCrews(crews);
        return PairMatchingMachine.makePair(missionGroup.getCourse(), shuffleCrews);
    }

    private void addPairs(MissionGroup missionGroup, List<Pair> pairs) {
        pairRepository.addPairs(missionGroup, pairs);
    }

    public void initMatchingHistory() {
        pairRepository.clear();
    }
}
