package com.salesforce.tooling.service;

import com.google.gson.JsonObject;


public interface SalesforceToolingApiService {

    JsonObject getToolingSObjects();

    JsonObject getToolingSObjectsName(String sObjectName);

    JsonObject getToolingSObjectsNameDescribe(String sObjectName);

    JsonObject getToolingSObjectsQuery(String query);
}
