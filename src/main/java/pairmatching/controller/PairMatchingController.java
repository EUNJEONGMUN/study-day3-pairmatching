package pairmatching.controller;

import pairmatching.domain.Command;
import pairmatching.domain.Menu;
import pairmatching.domain.MissionGroup;
import pairmatching.domain.Pair;
import pairmatching.service.CrewService;
import pairmatching.service.PairMatchingService;
import pairmatching.view.InputMessageView;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class PairMatchingController {
    private CrewService crewService = new CrewService();
    private PairMatchingService pairMatchingService = new PairMatchingService();

    public PairMatchingController() {
        crewService.initCrews();
    }

    public void run() {
        Menu menu;
        do {
            menu = read(Menu::from, InputView::readMenu);
            linkMenu(menu);
        } while (menu != Menu.QUIT);

    }

    private void linkMenu(Menu menu) {
        if (menu == Menu.PAIR_MATCHING) {
            runPairMatching();
        }
        if (menu == Menu.LOOKUP_PAIR) {
            // runLookupPair();
        }
        if (menu == Menu.INIT_PAIR) {
            // runInitPair();
        }
    }

    private void runPairMatching() {
        InputMessageView.showMission();
        MissionGroup missionGroup;
        boolean isContinue = false;
        do {
            missionGroup = read(MissionGroup::from, InputView::readCourseAndMission);
            if (pairMatchingService.isExistsPair(missionGroup)) {
                isContinue = read(Command::from, InputView::readCommand) == Command.NO;
            }
        } while(isContinue);
        pairMatchingService.createNotDuplicatePair(missionGroup);
    }

    private <T, R> R read(Function<T, R> object, Supplier<T> input) {
        try {
            return object.apply(input.get());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return read(object, input);
        }
    }
}