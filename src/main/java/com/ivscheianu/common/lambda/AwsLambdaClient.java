package com.ivscheianu.common.lambda;

import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvocationType;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.ivscheianu.common.property.AWSPropertiesProvider;

import java.nio.charset.StandardCharsets;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@Stateless
public class AwsLambdaClient implements LambdaClient {

    @Inject
    private AWSPropertiesProvider awsPropertiesProvider;

    private static final Jsonb JSONB = JsonbBuilder.create();

    @Override
    public String invokeLambda(final String lambdaName, final String payload) {
        final Regions region = awsPropertiesProvider.getRegion();
        final AWSSessionCredentials sessionCredentials = awsPropertiesProvider.getSessionCredentials();
        final AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(sessionCredentials);
        final InvokeRequest invokeRequest = new InvokeRequest()
            .withFunctionName(lambdaName)
            .withInvocationType(InvocationType.RequestResponse)
            .withPayload(JSONB.toJson(payload));
        final AWSLambda lambda = AWSLambdaClientBuilder
            .standard()
            .withRegion(region)
            .withCredentials(credentialsProvider)
            .build();
        final InvokeResult lambdaResult = lambda.invoke(invokeRequest);
        return new String(lambdaResult.getPayload().array(), StandardCharsets.UTF_8);
    }
}
