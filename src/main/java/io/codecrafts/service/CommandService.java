package io.codecrafts.service;

import io.codecrafts.CommandProcessor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class CommandService {
    public String process(String command) {
        CommandProcessor commandProcessor = new CommandProcessor(5, 5);
        LinkedList<String> input = commandProcessor.processInput(command);
        commandProcessor.process(input);
        return commandProcessor.getTable().getRobot().getCurrentX() + "," + commandProcessor.getTable().getRobot().getCurrentY() + "," + commandProcessor.getTable().getRobot().getCurrentDirection();
    }
}
