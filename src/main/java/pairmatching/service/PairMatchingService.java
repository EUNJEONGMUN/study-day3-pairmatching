package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.MissionGroup;
import pairmatching.domain.Pair;
import pairmatching.repository.PairRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PairMatchingService {
    private PairRepository pairRepository = new PairRepository();
    private CrewService crewService = new CrewService();

    public boolean isExistsPair(MissionGroup missionGroup) {
        return !pairRepository.findByMissionGroup(missionGroup).isEmpty();
    }

    public List<Pair> matchPair(MissionGroup missionGroup) {
        List<String> crews = crewService.getCrewsByCourse(missionGroup.getCourse());
        List<String> shuffleCrews = shuffleCrews(crews);
        return makePair(missionGroup.getCourse(), shuffleCrews);
    }

    private List<Pair> makePair(Course course, List<String> names) {
        List<Pair> result = new ArrayList<>();
        for (int i = 0; i < (names.size() / 2) * 2; i += 2) {
            result.add(Pair.of(Crew.of(course, names.get(i)), Crew.of(course, names.get(i + 1))));
        }
        if (names.size() != 0) {
            result.get(result.size() - 1)
                    .addCrew(Crew.of(course, names.get(names.size() - 1)));
        }
        return result;
    }

    private List<String> shuffleCrews(List<String> crews) {
        return Randoms.shuffle(crews);
    }
}
