package io.codecrafts.rest;

import io.codecrafts.CommandProcessor;
import io.codecrafts.model.Command;
import io.codecrafts.rest.annotations.RestApiController;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedList;

@RestApiController
public class CommandController {

    @PostMapping(value = "/command")
    public ResponseEntity<?> process(@RequestBody Command command) {
        CommandProcessor commandProcessor = new CommandProcessor(5, 5);

        LinkedList<String> input = commandProcessor.processInput(command.getCommand());

        commandProcessor.process(input);

        JSONObject json = new JSONObject();

        json.put("Output", commandProcessor.getTable().getRobot().getCurrentX() + "," + commandProcessor.getTable().getRobot().getCurrentY() + "," + commandProcessor.getTable().getRobot().getCurrentDirection());

        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }
}
