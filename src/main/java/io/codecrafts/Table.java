package io.codecrafts;

import java.util.HashMap;

enum EdgeType {
    NN,
    TE,
    BE,
    LE,
    RE,
    BL,
    BR,
    TL,
    TR,
}

public class Table {
    private static int NUM_X_CELLS = 5;
    private static int NUM_Y_CELLS = 5;
    private io.codecrafts.ToyRobot robot;
    HashMap<String, CellInfo> spots;
    String currentKey;
    int matrix[][] = new int[NUM_Y_CELLS][NUM_X_CELLS];

    public Table() {
        init();
        currentKey = "";
    }

    public void init() {
        spots = new HashMap<String, CellInfo>();
        for (int j = NUM_Y_CELLS - 1; j >= 0; j--)  {
            for (int i = 0; i < NUM_X_CELLS; i++) {
                String key = i + "," + j;
                spots.put(key, new CellInfo(EdgeType.NN, null));
            }
        }
        //reset();
    }

    public void reset() {
        for (int j = NUM_Y_CELLS - 1; j >= 0; j--)  {
            for (int i = 0; i < NUM_X_CELLS; i++) {
                String key = i + "," + j;
                spots.put(key, null);
            }
        }
    }

    public void placeRobot(int x, int y, Direction direction) {
        robot = new io.codecrafts.ToyRobot(x, y, direction);
        updateKey();
    }

    public void moveRobot() {
        if (robot != null) {
            boolean canMove = false;
            String key = robot.getCurrentX() + "," + robot.getCurrentY();
            switch(spots.get(key).getEdgeType()) {
                case TL:
                    if (robot.getCurrentDirection() == Direction.EAST || robot.getCurrentDirection() == Direction.SOUTH) {
                        canMove = true;
                    }
                    break;
                case TE:
                    if (robot.getCurrentDirection() == Direction.WEST || robot.getCurrentDirection() == Direction.EAST) {
                        canMove = true;
                    }
                    break;
                case TR:
                    if (robot.getCurrentDirection() == Direction.WEST || robot.getCurrentDirection() == Direction.SOUTH) {
                        canMove = true;
                    }
                    break;
                case LE:
                    if (robot.getCurrentDirection() == Direction.EAST || robot.getCurrentDirection() == io.codecrafts.Direction.NORTH  || robot.getCurrentDirection() == Direction.SOUTH) {
                        canMove = true;
                    }
                    break;
                case NN:
                    if (robot.getCurrentDirection() == Direction.WEST || robot.getCurrentDirection() == Direction.EAST || robot.getCurrentDirection() == io.codecrafts.Direction.NORTH  || robot.getCurrentDirection() == Direction.SOUTH) {
                        canMove = true;
                    }
                    break;
                case RE:
                    if (robot.getCurrentDirection() == Direction.WEST || robot.getCurrentDirection() == io.codecrafts.Direction.NORTH  || robot.getCurrentDirection() == Direction.SOUTH) {
                        canMove = true;
                    }
                    break;
                case BL:
                    if (robot.getCurrentDirection() == Direction.EAST || robot.getCurrentDirection() == io.codecrafts.Direction.NORTH) {
                        canMove = true;
                    }
                    break;
                case BE:
                    if (robot.getCurrentDirection() == Direction.WEST || robot.getCurrentDirection() == Direction.EAST || robot.getCurrentDirection() == io.codecrafts.Direction.NORTH) {
                        canMove = true;
                    }
                    break;
                case BR:
                    if (robot.getCurrentDirection() == Direction.WEST || robot.getCurrentDirection() == io.codecrafts.Direction.NORTH) {
                        canMove = true;
                    }
                    break;
            }

            if (canMove) {
                robot.move();
                updateKey();
            }
        }
    }

    public void calculateEdgeType() {
        for (int j = NUM_Y_CELLS - 1; j >= 0; j--)  {
            for (int i = 0; i < NUM_X_CELLS; i++) {
                if (i == 0 || j == 0 || i == NUM_Y_CELLS - 1 || j == NUM_X_CELLS - 1) {
                    String key = i + "," + j;
                    if (i == 0 && j == 0) {
                        spots.get(key).setEdgeType(EdgeType.BL);
                    } else if (j == NUM_Y_CELLS - 1 && i == 0) {
                        spots.get(key).setEdgeType(EdgeType.TL);
                    } else if (i == NUM_Y_CELLS - 1 && j == 0) {
                        spots.get(key).setEdgeType(EdgeType.BR);
                    } else if (i == NUM_Y_CELLS - 1 && j == NUM_X_CELLS - 1) {
                        spots.get(key).setEdgeType(EdgeType.TR);
                    } else if(j == 0) {
                        spots.get(key).setEdgeType(EdgeType.BE);
                    } else if(i == 0) {
                        spots.get(key).setEdgeType(EdgeType.LE);
                    } else if(j == NUM_X_CELLS - 1) {
                        spots.get(key).setEdgeType(EdgeType.TE);
                    } else if(i == NUM_Y_CELLS - 1) {
                        spots.get(key).setEdgeType(EdgeType.RE);
                    } else {
                        spots.get(key).setEdgeType(EdgeType.NN);
                    }
                }
            }
        }
    }

    public void rotateRobotClockwise() {
        robot.rotateClockwise();
    }

    public void rotateRobotAntiClockwise() {
        robot.rotateAntiClockwise();
    }

    public void report() {
        System.out.println("Output: " + robot.getCurrentX() + "," + robot.getCurrentY() + "," + robot.getCurrentDirection());
    }

    public void updateKey() {
        if (currentKey != "") {
            spots.get(currentKey).setRobot(null);
        }
        currentKey = robot.getCurrentX() + "," + robot.getCurrentY();
        spots.get(currentKey).setRobot(robot);
    }

    public void printTable() {
        for (int j = NUM_Y_CELLS - 1; j >= 0; j--)  {
            for (int i = 0; i < NUM_X_CELLS; i++) {
                String key = i + "," + j;
                ToyRobot robot = spots.get(key).getRobot();
                System.out.print(spots.get(key).getEdgeType()  + " " + (robot != null ? robot.getCurrentDirection() : " ") + " ");
            }
            System.out.println();
        }
    }

    public class CellInfo {

        private EdgeType edgeType;

        public ToyRobot getRobot() {
            return robot;
        }

        public void setRobot(ToyRobot robot) {
            this.robot = robot;
        }

        private ToyRobot robot;

        public CellInfo(EdgeType edgeType, ToyRobot robot) {
            this.setEdgeType(edgeType);
            this.setRobot(robot);
        }

        public EdgeType getEdgeType() {
            return edgeType;
        }

        public void setEdgeType(EdgeType edgeType) {
            this.edgeType = edgeType;
        }
    }
}
