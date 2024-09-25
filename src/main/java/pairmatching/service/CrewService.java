package pairmatching.service;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.repository.CrewRepository;
import pairmatching.view.OutputView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrewService {
    private static final String backendCrewPath = "src/main/resources/backend-crew.md";
    private static final String frontendCrewPath = "src/main/resources/frontend-crew.md";
    private CrewRepository crewRepository = new CrewRepository();

    public void initCrews() {
        try {
            List<String> backendCrewNames = readFile(backendCrewPath);
            List<String> frontendCrewNames = readFile(frontendCrewPath);
            saveCrews(backendCrewNames, Course.BACKEND);
            saveCrews(frontendCrewNames, Course.FRONTEND);
        } catch (IOException e) {
            OutputView.printErrorMessage("파일을 정상적으로 읽어올 수 없습니다.");
        }
    }

    public List<String> getCrewsByCourse(Course course) {
        return crewRepository.findByCourse(course);
    }

    private void saveCrews(List<String> names, Course course) {
        names.stream()
                .map(name -> Crew.of(course, name))
                .forEach(crewRepository::addCrew);
    }

    private List<String> readFile(String path) throws IOException {
        List<String> names = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String name;
        while ((name = reader.readLine()) != null) {
            names.add(name);
        }
        reader.close();
        return names;
    }
}