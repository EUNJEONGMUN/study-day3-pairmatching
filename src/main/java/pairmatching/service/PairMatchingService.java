package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.MissionGroup;
import pairmatching.domain.Pair;
import pairmatching.dto.PairsDto;
import pairmatching.repository.PairRepository;

import java.util.ArrayList;
import java.util.List;

public class PairMatchingService {
    private PairRepository pairRepository = new PairRepository();
    private CrewService crewService = new CrewService();

    public boolean isExistsPair(MissionGroup missionGroup) {
        return !pairRepository.findByMissionGroup(missionGroup).isEmpty();
    }

    public void createNotDuplicatePair(MissionGroup missionGroup) {
        for (int i=0; i<3; i++) {
            List<Pair> pairs = matchPair(missionGroup);
            if (!isDuplicated(missionGroup, pairs)) {
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
        List<String> shuffleCrews = shuffleCrews(crews);
        return makePair(missionGroup.getCourse(), shuffleCrews);
    }

    private boolean isDuplicated(MissionGroup missionGroup, List<Pair> pairs) {
        List<Pair> sameMissionPairs = pairRepository.findByMissionLevel(missionGroup);
        return pairs.stream()
                .anyMatch(pair -> sameMissionPairs.stream()
                        .anyMatch(sameMissionPair -> pair.isSamePair(sameMissionPair)));
    }

    private void addPairs(MissionGroup missionGroup, List<Pair> pairs) {
        pairRepository.addPairs(missionGroup, pairs);
    }

    private List<Pair> makePair(Course course, List<String> names) {
        List<Pair> result = new ArrayList<>();
        for (int i = 0; i < (names.size() / 2) * 2; i += 2) {
            result.add(Pair.of(Crew.of(course, names.get(i)), Crew.of(course, names.get(i + 1))));
        }
        if (names.size() % 2 != 0) {
            result.get(result.size() - 1)
                    .addCrew(Crew.of(course, names.get(names.size() - 1)));
        }
        return result;
    }

    private List<String> shuffleCrews(List<String> crews) {
        return Randoms.shuffle(crews);
    }
}
