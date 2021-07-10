package io.codecrafts;

import java.util.LinkedList;
import java.util.Scanner;

import static io.codecrafts.Utils.getDirectionFromString;

public class Main {
    public static void main(String[] args) {
        Processor p = new Processor();
        p.process(p.processInput());
    }
}
