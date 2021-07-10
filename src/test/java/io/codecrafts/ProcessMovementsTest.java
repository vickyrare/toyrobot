package io.codecrafts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class ProcessMovementsTest {

    Processor processor;
    @BeforeEach
    void setup() {
        processor = new Processor();
    }

    @Test
    void testMovements() {
        LinkedList<String> tokenList = new LinkedList<>();
        tokenList.add("PLACE");
        tokenList.add("1,2,EAST");
        tokenList.add("MOVE");
        tokenList.add("MOVE");
        tokenList.add("LEFT");
        tokenList.add("MOVE");
        tokenList.add("REPORT");
        processor.process(tokenList);
        assert(processor.getTable().getRobot().getCurrentX() == 3);
        assert(processor.getTable().getRobot().getCurrentY() == 3);
        assert(processor.getTable().getRobot().getCurrentDirection() == Direction.NORTH);
    }

    @Test
    void testTryToFallOffFromBottomLeftEdge() {
        LinkedList<String> tokenList = new LinkedList<>();
        tokenList.add("PLACE");
        tokenList.add("0,0,WEST");
        tokenList.add("MOVE");
        tokenList.add("REPORT");
        processor.process(tokenList);
        assert(processor.getTable().getRobot().getCurrentX() == 0);
        assert(processor.getTable().getRobot().getCurrentY() == 0);
        assert(processor.getTable().getRobot().getCurrentDirection() == Direction.WEST);
    }

    @Test
    void testRoundTripAroundEdges() {
        LinkedList<String> tokenList = new LinkedList<>();
        tokenList.add("PLACE");
        tokenList.add("0,0,EAST");
        tokenList.add("MOVE");
        tokenList.add("MOVE");
        tokenList.add("MOVE");
        tokenList.add("MOVE");
        tokenList.add("LEFT");
        tokenList.add("MOVE");
        tokenList.add("MOVE");
        tokenList.add("MOVE");
        tokenList.add("MOVE");
        tokenList.add("LEFT");
        tokenList.add("MOVE");
        tokenList.add("MOVE");
        tokenList.add("MOVE");
        tokenList.add("MOVE");
        tokenList.add("LEFT");
        tokenList.add("MOVE");
        tokenList.add("MOVE");
        tokenList.add("MOVE");
        tokenList.add("MOVE");
        tokenList.add("REPORT");
        processor.process(tokenList);
        assert(processor.getTable().getRobot().getCurrentX() == 0);
        assert(processor.getTable().getRobot().getCurrentY() == 0);
        assert(processor.getTable().getRobot().getCurrentDirection() == Direction.SOUTH);
    }

    @Test
    void testZigZagMovement() {
        LinkedList<String> tokenList = new LinkedList<>();
        tokenList.add("PLACE");

        tokenList.add("4,4,SOUTH");
        tokenList.add("MOVE");
        tokenList.add("RIGHT");
        tokenList.add("MOVE");

        tokenList.add("LEFT");
        tokenList.add("MOVE");
        tokenList.add("RIGHT");
        tokenList.add("MOVE");

        tokenList.add("LEFT");
        tokenList.add("MOVE");
        tokenList.add("RIGHT");
        tokenList.add("MOVE");

        tokenList.add("LEFT");
        tokenList.add("MOVE");
        tokenList.add("RIGHT");
        tokenList.add("MOVE");
        tokenList.add("REPORT");
        processor.process(tokenList);
        assert(processor.getTable().getRobot().getCurrentX() == 0);
        assert(processor.getTable().getRobot().getCurrentY() == 0);
        assert(processor.getTable().getRobot().getCurrentDirection() == Direction.WEST);
    }
}
