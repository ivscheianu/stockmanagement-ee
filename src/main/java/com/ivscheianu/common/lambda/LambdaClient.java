package com.ivscheianu.common.lambda;

public interface LambdaClient {
    String invokeLambda(final String lambdaName, final String payload);
}
