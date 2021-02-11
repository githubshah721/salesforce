package com.salesforce.tooling.service;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;


public interface SalesforceToolingApiService {

    JsonObject getToolingSObjects() throws IOException;

    JsonObject getToolingSObjectsName(String sObjectName) throws IOException;

    JsonObject getToolingSObjectsNameDescribe(String sObjectName) throws IOException;

    JsonObject getToolingSObjectsQuery(String query) throws IOException;

    default JsonObject getDataFrom(Call<JsonObject> call) throws IOException {
        Response<JsonObject> execute = call.execute();
        return execute.body();
    }
}
