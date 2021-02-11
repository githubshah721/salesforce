package com.salesforce.tooling.client;

import com.salesforce.tooling.client.pojo.Token;
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
public class SalesForceToolingClientImpl implements SalesForceToolingClient {
    @Value("${salesforce.instance}")
    private String BASE_URL;

    @Value("${salesforce.grant_type}")
    private String GRANT_TYPE;

    @Value("${salesforce.client_id}")
    private String CLIENT_ID;

    @Value("${salesforce.client_secret}")
    private String CLIENT_SECRET;

    @Value("${salesforce.username}")
    private String USER_NAME;

    @Value("${salesforce.password}")
    private String PASSWORD;

    @Value("${salesforce.auth_url}")
    private String AUTH_URL;

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
        String url = String.format("%s?grant_type=%s&client_id=%s&client_secret=%s&username=%s&password=%s",
            AUTH_URL, GRANT_TYPE, CLIENT_ID, CLIENT_SECRET, USER_NAME, PASSWORD);
        ResponseEntity<Token> response = restTemplate.postForEntity(url, new HttpEntity<>(new HashMap()), Token.class);
        Token accessToken = response.getBody();
        return accessToken.getAccess_token();
    }
}