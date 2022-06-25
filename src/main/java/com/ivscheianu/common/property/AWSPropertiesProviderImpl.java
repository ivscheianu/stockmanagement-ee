package com.ivscheianu.common.property;

import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class AWSPropertiesProviderImpl extends AbstractPropertiesReader implements AWSPropertiesProvider {

    private static final String CREDENTIALS_FILE = "aws.properties";
    private static final String AWS_ACCESS_KEY = "aws_access_key_id";
    private static final String AWS_SECRET_KEY = "aws_secret_access_key";
    private static final String AWS_SESSION_TOKEN = "aws_session_token";
    private static final String REGION = "region";

    private AWSSessionCredentials awsSessionCredentials;
    private Regions region;

    @Override
    public AWSSessionCredentials getSessionCredentials() {
        return awsSessionCredentials;
    }

    @Override
    public Regions getRegion() {
        return region;
    }

    @PostConstruct
    private void postConstruct() {
        final Properties properties = loadProperties();
        awsSessionCredentials = new BasicSessionCredentials(
            properties.getProperty(AWS_ACCESS_KEY),
            properties.getProperty(AWS_SECRET_KEY),
            properties.getProperty(AWS_SESSION_TOKEN)
        );
        region = Regions.fromName(properties.getProperty(REGION));
    }

    @Override
    protected String getPropertiesFileName() {
        return CREDENTIALS_FILE;
    }
}
