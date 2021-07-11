package io.codecrafts.rest;

import io.codecrafts.model.Command;
import io.codecrafts.rest.annotations.RestApiController;
import io.codecrafts.service.CommandService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestApiController
public class CommandController {

    @Autowired
    CommandService commandService;

    @PostMapping(value = "/command")
    public ResponseEntity<?> process(@RequestBody Command command) {
        String result = commandService.process(command.getCommand());
        JSONObject json = new JSONObject();
        json.put("Output", result);
        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }
}
