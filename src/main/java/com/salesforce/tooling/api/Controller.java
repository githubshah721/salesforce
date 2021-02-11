package com.salesforce.tooling.api;

import com.google.gson.JsonObject;
import com.salesforce.tooling.service.ToolingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private ToolingService toolingService;

    @GetMapping
    JsonObject getToolingSObjects(){
       return toolingService.getToolingSObjects();
    }

}
