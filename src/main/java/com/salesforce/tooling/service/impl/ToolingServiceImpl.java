package com.salesforce.tooling.service.impl;


import com.google.gson.JsonObject;
import com.salesforce.tooling.client.SalesForceToolingServiceGenerator;
import com.salesforce.tooling.client.SalesServicePP;
import com.salesforce.tooling.client.ToolingSObjects;
import com.salesforce.tooling.service.ToolingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        ToolingSObjects service1 = salesServicePP.createService(ToolingSObjects.class, "Bearer 00D5g000004EdH3!ARYAQCwqt0Zt1caf.QEPGUFnTfU1RzOFfiypsN2YH6upZlzXW.cVBLu48SutzOpPfzSAs4U2..EhUEYncD93xT0hmNqhgaLu");
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
}
