package com.salesforce.tooling.service.impl;


import com.google.gson.JsonObject;
import com.salesforce.tooling.client.SalesServicePP;
import com.salesforce.tooling.client.ToolingApiService;
import com.salesforce.tooling.service.SalesforceToolingApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class SalesforceToolingApiServiceImpl implements SalesforceToolingApiService {

    private ToolingApiService toolingApiService;

    private SalesforceToolingApiServiceImpl(@Autowired SalesServicePP salesServicePP) {
        toolingApiService = salesServicePP.createService(ToolingApiService.class);
    }

    @Override
    public JsonObject getToolingSObjects() throws IOException {
        return getDataFrom(toolingApiService.getSObjects());
    }

    @Override
    public JsonObject getToolingSObjectsName(String sObjectName) throws IOException {
        return getDataFrom(toolingApiService.getSObjectsName(sObjectName));
    }

    @Override
    public JsonObject getToolingSObjectsNameDescribe(String sObjectName) throws IOException {
        return getDataFrom(toolingApiService.getSObjectsNameDescribe(sObjectName));
    }

    @Override
    public JsonObject getToolingSObjectsQuery(String query) throws IOException {
        return getDataFrom(toolingApiService.getSObjectsQuery(query));
    }
}
