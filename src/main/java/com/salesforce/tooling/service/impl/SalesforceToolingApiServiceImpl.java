package com.salesforce.tooling.service.impl;


import com.google.gson.JsonObject;
import com.salesforce.tooling.client.SalesForceToolingClient;
import com.salesforce.tooling.client.ToolingApiService;
import com.salesforce.tooling.service.SalesforceToolingApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class SalesforceToolingApiServiceImpl implements SalesforceToolingApiService {

    private ToolingApiService toolingApiService;

    private SalesforceToolingApiServiceImpl(@Autowired SalesForceToolingClient salesForceToolingClient) {
        toolingApiService = salesForceToolingClient.createService(ToolingApiService.class);
    }

    @Override
    public JsonObject getSObjects() throws IOException {
        return getDataFrom(toolingApiService.getSObjects());
    }

    @Override
    public JsonObject getSObjectsByName(String sObjectName) throws IOException {
        return getDataFrom(toolingApiService.getSObjectsName(sObjectName));
    }

    @Override
    public JsonObject getSObjectsDescribeByName(String sObjectName) throws IOException {
        return getDataFrom(toolingApiService.getSObjectsNameDescribe(sObjectName));
    }

    @Override
    public JsonObject getSObjectsByQuery(String query) throws IOException {
        return getDataFrom(toolingApiService.getSObjectsQuery(query));
    }
}
