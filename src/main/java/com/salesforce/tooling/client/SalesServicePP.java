package com.salesforce.tooling.client;

public interface SalesServicePP {
    <S> S createService(Class<S> serviceClass, String token);
}
