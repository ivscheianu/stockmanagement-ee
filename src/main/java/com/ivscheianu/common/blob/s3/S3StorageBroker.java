package com.ivscheianu.common.blob.s3;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ivscheianu.common.blob.BlobStorageBroker;

import java.io.InputStream;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class S3StorageBroker implements BlobStorageBroker {

    @Inject
    private S3ClientFactory s3ClientFactory;

    @Override
    public void upload(final String bucketName, final String key, final InputStream inputStream) {
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
        s3ClientFactory
            .createClient()
            .putObject(bucketName, key, inputStream, objectMetadata);
    }

    @Override
    public void createBucket(final String bucketName) {
        s3ClientFactory.createClient().createBucket(bucketName);
    }

    @Override
    public String getLink(final String bucketName, final String key) {
        return s3ClientFactory
            .createClient()
            .getObject(bucketName, key)
            .getObjectContent()
            .getHttpRequest()
            .getURI()
            .toString();
    }
}
