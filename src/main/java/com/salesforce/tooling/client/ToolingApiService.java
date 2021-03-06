package com.salesforce.tooling.client;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ToolingApiService {
    @GET("/services/data/v50.0/tooling/sobjects/")
    Call<JsonObject> getSObjects();

    @GET("/services/data/v50.0/tooling/sobjects/{sObjectName}")
    Call<JsonObject> getSObjectsName(@Path("sObjectName") String sObjectName);

    @GET("/services/data/v50.0/tooling/sobjects/{sObjectName}/describe")
    Call<JsonObject> getSObjectsNameDescribe(@Path("sObjectName") String sObjectName);

    @GET("/services/data/v50.0/query/")
    Call<JsonObject> getSObjectsQuery(@Query("q") String q);
}
