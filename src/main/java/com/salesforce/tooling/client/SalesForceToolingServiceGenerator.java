package com.salesforce.tooling.client;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;

@Service
public class SalesForceToolingServiceGenerator implements SalesServicePP {
    @Value("${salesforce.instance}")
    private String BASE_URL;

    private Retrofit.Builder builder;
    private Retrofit retrofit;
    private final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    @PostConstruct
    void init() {
        builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
    }

    @Override
    public <S> S createService(Class<S> serviceClass) {
        String token = getAccessToken();
        if (token != null) {
            httpClient.interceptors().clear();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder builder = original.newBuilder().header("Authorization", "Bearer " + token);
                    Request request = builder.build();
                    return chain.proceed(request);
                }
            });
            builder.client(httpClient.build());
            retrofit = builder.build();
        }

        return retrofit.create(serviceClass);
    }

    RestTemplate restTemplate = new RestTemplate();

    private String getAccessToken() {
        String url = "https://login.salesforce.com/services/oauth2/token?" +
            "grant_type=password&" +
            "client_id=3MVG9fe4g9fhX0E5mrMd_Kq1IykXBy7kQXgKJeJZj0NhvJoO4CLR231N3zv8w4TWVyu74LLxkekqTzMC5gSs5&" +
            "client_secret=0D0D8EEF93AEBA8D1195D3F42DF80E3B9A9B33EAAE96EFD1CBA1F2E52708DF06&" +
            "username=shaid.hussain721@gmail.com&" +
            "password=salesforce@721FbIVKW9TDuzP7HGOI4hY7RSi";
        ResponseEntity<Token> response = restTemplate.postForEntity(url, new HttpEntity<>(new HashMap()), Token.class);
        Token body = response.getBody();
        return body.access_token;
    }
}