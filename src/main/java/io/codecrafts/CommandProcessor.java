package io.codecrafts;

import java.util.LinkedList;
import java.util.Scanner;

import static io.codecrafts.Utils.getDirectionFromString;

public class CommandProcessor {

    private Table table;

    private int numXCells;
    private int numYCells;

    public CommandProcessor(int numXCells, int numYCells){
        this.setNumXCells(numXCells);
        this.setNumYCells(numYCells);
    }

    public LinkedList<String> processInput(String query) {
        LinkedList<String> tokenList = new LinkedList<>();
        if (!query.isEmpty()) {
            String[] tokens = query.split(" ");
            if (tokens.length < 2) {
                System.out.println("Invalid input.");
            } else {
                tokenList = new LinkedList<>();
                for (String token : tokens) {
                    tokenList.add(token);
                }
            }
        }
        return tokenList;
    }

    public LinkedList<String> processInput() {
        Scanner sc = new Scanner(System.in);
        LinkedList<String> tokenList = new LinkedList<>();
        String input = sc.nextLine();
        if (!input.isEmpty()) {
            String[] tokens = input.split(" ");
            if (tokens.length < 2) {
                System.out.println("Invalid input.");
            } else {
                tokenList = new LinkedList<>();
                for (String token : tokens) {
                    tokenList.add(token);
                }
            }
        }
        return tokenList;
    }

    public void process(LinkedList<String> tokenList) {
        table = new Table(numXCells, numYCells);
        getTable().calculateEdgeType();

        String place = tokenList.pop();
        String location = tokenList.pop();
        String locationTokens[] = location.split(",");

        if ((Integer.parseInt(locationTokens[0]) < 0 || Integer.parseInt(locationTokens[0]) >= numXCells)
                || (Integer.parseInt(locationTokens[1]) < 0 || Integer.parseInt(locationTokens[1]) >= numYCells)) {
            System.out.println("Output: ROBOT MISSING");
            return;
        }

        if (place.equals("PLACE") && locationTokens.length == 3) {
            Direction direction = getDirectionFromString(locationTokens[2]);
            getTable().placeRobot(Integer.parseInt(locationTokens[0]), Integer.parseInt(locationTokens[1]), direction);
            while (tokenList.size() > 0) {
                String currentCommand = tokenList.pop();
                switch (currentCommand) {
                    case "MOVE":
                        getTable().moveRobot();
                        break;
                    case "LEFT":
                        getTable().rotateRobotAntiClockwise();
                        break;
                    case "RIGHT":
                        getTable().rotateRobotClockwise();
                        break;
                    case "REPORT":
                        getTable().printTable();
                        getTable().report();
                        break;
                }
            }
        }
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public int getNumXCells() {
        return numXCells;
    }

    public void setNumXCells(int numXCells) {
        this.numXCells = numXCells;
    }

    public int getNumYCells() {
        return numYCells;
    }

    public void setNumYCells(int numYCells) {
        this.numYCells = numYCells;
    }
}
