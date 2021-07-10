package io.codecrafts;

import java.util.LinkedList;
import java.util.Scanner;

import static io.codecrafts.Utils.getDirectionFromString;

public class Processor {

    private Table table;

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
        String place = tokenList.pop();
        String location = tokenList.pop();
        String locationTokens[] = location.split(",");
        if (place.equals("PLACE") && locationTokens.length == 3) {
            setTable(new Table(5, 5));
            getTable().calculateEdgeType();
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
}
