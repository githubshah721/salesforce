package com.salesforce.tooling.service.impl;


import com.google.gson.JsonObject;
import com.salesforce.tooling.client.SalesServicePP;
import com.salesforce.tooling.client.ToolingSObjects;
import com.salesforce.tooling.service.ToolingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;


@Service
public class ToolingServiceImpl implements ToolingService {

    @Autowired
    private SalesServicePP salesServicePP;

    @Override
    public JsonObject getToolingSObjects() {
        ToolingSObjects service1 = salesServicePP.createService(ToolingSObjects.class);
        Call<JsonObject> allUser123 = service1.getSObjects();
        try {
            Response<JsonObject> execute = allUser123.execute();
            JsonObject body = execute.body();
            System.out.println(body);
            return body;
        } catch (IOException ex) {

        }
        return null;
    }

    @Override
    public JsonObject getToolingSObjectsName(String sObjectName) {
        ToolingSObjects service1 = salesServicePP.createService(ToolingSObjects.class);
        Call<JsonObject> allUser123 = service1.getSObjectsName(sObjectName);
        try {
            Response<JsonObject> execute = allUser123.execute();
            JsonObject body = execute.body();
            System.out.println(body);
            return body;
        } catch (IOException ex) {

        }
        return null;
    }
}
