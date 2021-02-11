package com.salesforce.tooling.client;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ToolingSObjects {
    @GET("/services/data/v50.0/tooling/sobjects/")
    Call<JsonObject> getSObjects();
}
