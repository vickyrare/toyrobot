package io.codecrafts;

public class Utils {
    public static Direction getDirectionFromString(String direction) {
        switch (direction) {
            case "NORTH":
                return Direction.NORTH;
            case "WEST":
                return Direction.WEST;
            case "EAST":
                return Direction.EAST;
            case "SOUTH":
                return Direction.SOUTH;
        }

        return Direction.NONE;
    }
}
