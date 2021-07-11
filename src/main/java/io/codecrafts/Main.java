package io.codecrafts;

public class Main {
    public static void main(String[] args) {
        CommandProcessor p = new CommandProcessor(5, 5);
        p.process(p.processInput());
    }
}
