package com.salesforce.tooling.api;

import com.google.gson.JsonObject;
import com.salesforce.tooling.service.ToolingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class Controller {

    @Autowired
    private ToolingService toolingService;

    @RequestMapping("")
    JsonObject getToolingSObjects() {
        return toolingService.getToolingSObjects();
    }

    @RequestMapping("/{sObjectName}")
    JsonObject getToolingSObjectsName(@PathVariable String sObjectName) {
        System.out.println("SObjectName: " + sObjectName);
        return toolingService.getToolingSObjectsName(sObjectName);
    }

    @RequestMapping("/{sObjectName}/describe")
    JsonObject getToolingSObjectsDescribe(@PathVariable String sObjectName) {
        System.out.println("SObjectName: " + sObjectName);
        return toolingService.getToolingSObjectsNameDescribe(sObjectName);
    }

    @RequestMapping("/my")
    JsonObject getToolingSObjectsQuery(@PathParam("q") String q) {
        return toolingService.getToolingSObjectsQuery(q);
    }
}
