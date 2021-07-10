package io.codecrafts;

import java.util.HashMap;

public class Table {
    private int numXCells;
    private int numYCell;
    private ToyRobot robot;
    private HashMap<String, CellInfo> spots;
    private String currentKey;

    public Table(int numXCells, int numYCells){
        this.setNumXCells(numXCells);
        this.setNumYCell(numYCells);
        init();
        currentKey = "";
        calculateEdgeType();
    }

    public void init() {
        setSpots(new HashMap<String, CellInfo>());
        for (int j = getNumYCell() - 1; j >= 0; j--)  {
            for (int i = 0; i < getNumXCells(); i++) {
                String key = i + "," + j;
                getSpots().put(key, new CellInfo(EdgeType.NE, null));
            }
        }
    }

    public void placeRobot(int x, int y, Direction direction) {
        setRobot(new ToyRobot(x, y, direction));
        updateKey();
    }

    public void moveRobot() {
        if (getRobot() != null) {
            boolean canMove = false;
            String key = getRobot().getCurrentX() + "," + getRobot().getCurrentY();
            switch(getSpots().get(key).getEdgeType()) {
                case TL:
                    if (getRobot().getCurrentDirection() == Direction.EAST || getRobot().getCurrentDirection() == Direction.SOUTH) {
                        canMove = true;
                    }
                    break;
                case TE:
                    if (getRobot().getCurrentDirection() == Direction.WEST || getRobot().getCurrentDirection() == Direction.EAST) {
                        canMove = true;
                    }
                    break;
                case TR:
                    if (getRobot().getCurrentDirection() == Direction.WEST || getRobot().getCurrentDirection() == Direction.SOUTH) {
                        canMove = true;
                    }
                    break;
                case LE:
                    if (getRobot().getCurrentDirection() == Direction.EAST || getRobot().getCurrentDirection() == io.codecrafts.Direction.NORTH  || getRobot().getCurrentDirection() == Direction.SOUTH) {
                        canMove = true;
                    }
                    break;
                case NE:
                    if (getRobot().getCurrentDirection() == Direction.WEST || getRobot().getCurrentDirection() == Direction.EAST || getRobot().getCurrentDirection() == io.codecrafts.Direction.NORTH  || getRobot().getCurrentDirection() == Direction.SOUTH) {
                        canMove = true;
                    }
                    break;
                case RE:
                    if (getRobot().getCurrentDirection() == Direction.WEST || getRobot().getCurrentDirection() == io.codecrafts.Direction.NORTH  || getRobot().getCurrentDirection() == Direction.SOUTH) {
                        canMove = true;
                    }
                    break;
                case BL:
                    if (getRobot().getCurrentDirection() == Direction.EAST || getRobot().getCurrentDirection() == io.codecrafts.Direction.NORTH) {
                        canMove = true;
                    }
                    break;
                case BE:
                    if (getRobot().getCurrentDirection() == Direction.WEST || getRobot().getCurrentDirection() == Direction.EAST || getRobot().getCurrentDirection() == io.codecrafts.Direction.NORTH) {
                        canMove = true;
                    }
                    break;
                case BR:
                    if (getRobot().getCurrentDirection() == Direction.WEST || getRobot().getCurrentDirection() == io.codecrafts.Direction.NORTH) {
                        canMove = true;
                    }
                    break;
            }

            if (canMove) {
                getRobot().move();
                updateKey();
            }
        }
    }

    public void calculateEdgeType() {
        for (int j = getNumYCell() - 1; j >= 0; j--)  {
            for (int i = 0; i < getNumXCells(); i++) {
                if (i == 0 || j == 0 || i == getNumYCell() - 1 || j == getNumXCells() - 1) {
                    String key = i + "," + j;
                    if (i == 0 && j == 0) {
                        getSpots().get(key).setEdgeType(EdgeType.BL);
                    } else if (j == getNumYCell() - 1 && i == 0) {
                        getSpots().get(key).setEdgeType(EdgeType.TL);
                    } else if (i == getNumYCell() - 1 && j == 0) {
                        getSpots().get(key).setEdgeType(EdgeType.BR);
                    } else if (i == getNumYCell() - 1 && j == getNumXCells() - 1) {
                        getSpots().get(key).setEdgeType(EdgeType.TR);
                    } else if(j == 0) {
                        getSpots().get(key).setEdgeType(EdgeType.BE);
                    } else if(i == 0) {
                        getSpots().get(key).setEdgeType(EdgeType.LE);
                    } else if(j == getNumXCells() - 1) {
                        getSpots().get(key).setEdgeType(EdgeType.TE);
                    } else if(i == getNumYCell() - 1) {
                        getSpots().get(key).setEdgeType(EdgeType.RE);
                    } else {
                        getSpots().get(key).setEdgeType(EdgeType.NE);
                    }
                }
            }
        }
    }

    public void rotateRobotClockwise() {
        getRobot().rotateClockwise();
    }

    public void rotateRobotAntiClockwise() {
        getRobot().rotateAntiClockwise();
    }

    public void report() {
        System.out.println("Output: " + getRobot().getCurrentX() + "," + getRobot().getCurrentY() + "," + getRobot().getCurrentDirection());
    }

    public void updateKey() {
        if (currentKey != "") {
            getSpots().get(currentKey).setRobot(null);
        }
        currentKey = getRobot().getCurrentX() + "," + getRobot().getCurrentY();
        getSpots().get(currentKey).setRobot(getRobot());
    }

    public void printTable() {
        for (int j = getNumYCell() - 1; j >= 0; j--)  {
            for (int i = 0; i < getNumXCells(); i++) {
                String key = i + "," + j;
                ToyRobot robot = getSpots().get(key).getRobot();
                System.out.print(getSpots().get(key).getEdgeType()  + " " + (robot != null ? robot.getCurrentDirection() : " ") + " ");
            }
            System.out.println();
        }
    }

    public ToyRobot getRobot() {
        return robot;
    }

    public void setRobot(ToyRobot robot) {
        this.robot = robot;
    }

    public HashMap<String, CellInfo> getSpots() {
        return spots;
    }

    public void setSpots(HashMap<String, CellInfo> spots) {
        this.spots = spots;
    }

    public int getNumXCells() {
        return numXCells;
    }

    public void setNumXCells(int numXCells) {
        this.numXCells = numXCells;
    }

    public int getNumYCell() {
        return numYCell;
    }

    public void setNumYCell(int numYCell) {
        this.numYCell = numYCell;
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
