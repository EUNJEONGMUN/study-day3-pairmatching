package pairmatching.domain;

public enum Mission {
    RACING_CAR(Level.LEVEL1, "자동차 경주"),
    LOTTO(Level.LEVEL1, "로또"),
    BASEBALL(Level.LEVEL1, "숫자야구게임"),
    SHOPPING(Level.LEVEL2, "장바구니"),
    PAYMENT(Level.LEVEL2, "결제"),
    SUBWAY(Level.LEVEL2, "지하철노선도"),
    REFACTORING(Level.LEVEL4, "성능개선"),
    RELEASE(Level.LEVEL4, "배포");

    private final Level level;
    private final String name;

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
    }
}