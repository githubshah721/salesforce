package com.salesforce.tooling.client;

public interface SalesForceToolingClient {
    <S> S createService(Class<S> serviceClass);
}
