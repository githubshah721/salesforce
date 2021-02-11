package com.salesforce.tooling.api;

import com.google.gson.JsonObject;
import com.salesforce.tooling.service.ToolingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private ToolingService toolingService;

    @GetMapping
    JsonObject getToolingSObjects(){
       return toolingService.getToolingSObjects();
    }

    @RequestMapping("/{sObjectName}")
    JsonObject getToolingSObjectsName(@PathVariable String sObjectName){
        System.out.println("SObjectName: " + sObjectName);
        return toolingService.getToolingSObjectsName(sObjectName);
    }

}
