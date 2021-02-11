package com.salesforce.tooling.api;

import com.google.gson.JsonObject;
import com.salesforce.tooling.service.SalesforceToolingApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.IOException;

@RestController
public class Controller {

    @Autowired
    private SalesforceToolingApiService salesforceToolingApiService;

    /**
     * @return Lists the available Tooling API objects and their metadata
     */
    @RequestMapping("")
    JsonObject getToolingSObjects() throws IOException {
        return salesforceToolingApiService.getSObjects();
    }

    /**
     * @param sObjectName : sObjectName
     * @return Describe the individual metadata for the specified object:
     */
    @RequestMapping("/{sObjectName}")
    JsonObject getToolingSObjectsName(@PathVariable String sObjectName) throws IOException {
        System.out.println("SObjectName: " + sObjectName);
        return salesforceToolingApiService.getSObjectsByName(sObjectName);
    }

    /**
     * @param sObjectName : sObjectName
     * @return Completely describes the individual metadata at all levels for the specified object.
     */
    @RequestMapping("/{sObjectName}/describe")
    JsonObject getToolingSObjectsDescribe(@PathVariable String sObjectName) throws IOException {
        System.out.println("SObjectName: " + sObjectName);
        return salesforceToolingApiService.getSObjectsDescribeByName(sObjectName);
    }

    /**
     * Execute a Salesforce SOQL query against an object.
     *
     * @param q = SOQL query.
     * @return data that matches the specified criteria.
     */
    @RequestMapping("/my")
    JsonObject getToolingSObjectsQuery(@PathParam("q") String q) throws IOException {
        return salesforceToolingApiService.getSObjectsByQuery(q);
    }
}
