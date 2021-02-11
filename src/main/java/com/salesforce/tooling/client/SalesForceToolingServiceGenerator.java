package com.salesforce.tooling.client;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class SalesForceToolingServiceGenerator implements SalesServicePP{
    @Value("${salesforce.instance}")
    private String BASE_URL;

    private Retrofit.Builder builder;
    private Retrofit retrofit;
    private final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    @PostConstruct
    void init(){
        builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
    }

    @Override
    public <S> S createService(Class<S> serviceClass, final String token) {
        if (token != null) {
            httpClient.interceptors().clear();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder builder = original.newBuilder().header("Authorization", token);
                    Request request = builder.build();
                    return chain.proceed(request);
                }
            });
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        return retrofit.create(serviceClass);
    }
}