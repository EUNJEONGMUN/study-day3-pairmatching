package pairmatching.dto;

import pairmatching.domain.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class PairsDto {
    private final List<Pair> pairs;

    private PairsDto(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public static PairsDto of(List<Pair> pairs) {
        return new PairsDto(pairs);
    }

    public List<List<String>> getPairNames() {
        return pairs.stream()
                .map(Pair::getNames)
                .collect(Collectors.toUnmodifiableList());
    }
}
