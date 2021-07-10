package io.codecrafts;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CalculateEdgeTypeTest {

    Table table;

    @ParameterizedTest
    @MethodSource("withKeyAndEdgeType3X3")
    void testCalculateEdgeType3X3(String key, EdgeType edgeType) {
        table = new Table(3,3);
        assert(table.getSpots().get(key).getEdgeType() == edgeType);
    }

    private static Stream<Arguments> withKeyAndEdgeType3X3() {
        return Stream.of(
            Arguments.of("0,2", EdgeType.TL), Arguments.of("1,2", EdgeType.TE), Arguments.of("2,2", EdgeType.TR),
            Arguments.of("0,1", EdgeType.LE), Arguments.of("1,1", EdgeType.NE), Arguments.of("2,1", EdgeType.RE),
            Arguments.of("0,0", EdgeType.BL), Arguments.of("1,0", EdgeType.BE), Arguments.of("2,0", EdgeType.BR));
    }

    @ParameterizedTest
    @MethodSource("withKeyAndEdgeType5X5")
    void testCalculateEdgeType5X5(String key, EdgeType edgeType) {
        table = new Table(5,5);
        assert(table.getSpots().get(key).getEdgeType() == edgeType);
    }

    private static Stream<Arguments> withKeyAndEdgeType5X5() {
        return Stream.of(
            Arguments.of("0,4", EdgeType.TL), Arguments.of("1,4", EdgeType.TE), Arguments.of("2,4", EdgeType.TE), Arguments.of("3,4", EdgeType.TE), Arguments.of("4,4", EdgeType.TR),
            Arguments.of("0,3", EdgeType.LE), Arguments.of("1,3", EdgeType.NE), Arguments.of("2,3", EdgeType.NE), Arguments.of("3,3", EdgeType.NE), Arguments.of("4,3", EdgeType.RE),
            Arguments.of("0,2", EdgeType.LE), Arguments.of("1,2", EdgeType.NE), Arguments.of("2,2", EdgeType.NE), Arguments.of("3,2", EdgeType.NE), Arguments.of("4,2", EdgeType.RE),
            Arguments.of("0,1", EdgeType.LE), Arguments.of("1,2", EdgeType.NE), Arguments.of("2,1", EdgeType.NE), Arguments.of("3,1", EdgeType.NE), Arguments.of("4,1", EdgeType.RE),
            Arguments.of("0,0", EdgeType.BL), Arguments.of("1,0", EdgeType.BE), Arguments.of("2,0", EdgeType.BE), Arguments.of("3,0", EdgeType.BE), Arguments.of("4,0", EdgeType.BR));
    }
}
