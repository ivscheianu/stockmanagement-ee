package com.ivscheianu.common.blob.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.ivscheianu.common.property.AWSPropertiesProvider;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class S3ClientFactory {

    @Inject
    private AWSPropertiesProvider awsPropertiesProvider;

    public AmazonS3 createClient() {
        final AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(awsPropertiesProvider.getSessionCredentials());
        return AmazonS3ClientBuilder
            .standard()
            .withCredentials(credentialsProvider)
            .withRegion(awsPropertiesProvider.getRegion())
            .build();
    }
}
