package io.codecrafts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class ProcessMovementsTest {



    @Test
    public void testMovements() {
        Processor processor = new Processor(5,5);
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
    public void testPlaceRobotOutsideTable() {
        Processor processor = new Processor(5,5);
        LinkedList<String> tokenList = new LinkedList<>();
        tokenList.add("PLACE");
        tokenList.add("4,5,EAST");
        tokenList.add("MOVE");
        tokenList.add("MOVE");
        tokenList.add("LEFT");
        tokenList.add("MOVE");
        tokenList.add("REPORT");
        processor.process(tokenList);
        assert(processor.getTable().getRobot() == null);
    }

    @Test
    public void testTryToFallOffFromBottomLeftEdge() {
        Processor processor = new Processor(5,5);
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
    public void testRoundTripAroundEdges() {
        Processor processor = new Processor(5,5);
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
    public void testZigZagMovement() {
        Processor processor = new Processor(5,5);
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
