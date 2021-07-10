package io.codecrafts;

import java.util.LinkedList;
import java.util.Scanner;

import static io.codecrafts.Utils.getDirectionFromString;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (!input.isEmpty()) {
                String[] tokens = input.split(" ");
                if (tokens.length < 2) {
                    System.out.println("Invalid input.");
                } else {
                    Table table = new Table();
                    table.calculateEdgeType();

                    LinkedList<String> tokenList = new LinkedList<>();
                    for (String token : tokens) {
                        tokenList.add(token);
                    }

                    //PLACE 0,0,NORTH MOVE REPORT
                    String place = tokenList.pop();
                    String location = tokenList.pop();
                    String locationTokens[] = location.split(",");
                    if (locationTokens.length == 3) {
                        Direction direction = getDirectionFromString(locationTokens[2]);
                        table.placeRobot(Integer.parseInt(locationTokens[0]), Integer.parseInt(locationTokens[1]), direction);
                        while(tokenList.size() > 0) {
                            String currentCommand = tokenList.pop();
                            switch (currentCommand) {
                                case "MOVE":
                                    table.moveRobot();
                                    break;
                                case "LEFT":
                                    table.rotateRobotAntiClockwise();
                                    break;
                                case "RIGHT":
                                    table.rotateRobotClockwise();
                                    break;
                                case "REPORT":
                                    table.printTable();
                                    table.report();
                                    break;
                            }
                        }
                    } else {
                        System.out.println("ROBOT MISSING");
                    }
                }
            }
        }
    }
}
