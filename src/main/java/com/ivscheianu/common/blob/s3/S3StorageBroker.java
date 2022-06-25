package com.ivscheianu.common.blob.s3;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ivscheianu.common.blob.BlobStorageBroker;

import java.io.InputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class S3StorageBroker implements BlobStorageBroker {

    private static final ObjectMetadata DEFAULT_METADATA = constructDefaultMetadata();

    @Inject
    private S3ClientFactory s3ClientFactory;

    @Override
    public void upload(final String bucketName, final String key, final InputStream inputStream) {
        s3ClientFactory
            .createClient()
            .putObject(bucketName, key, inputStream, DEFAULT_METADATA);
    }

    @Override
    public void publicUpload(final String bucketName, final String key, final InputStream inputStream) {
        final PutObjectRequest putObjectRequest =
            new PutObjectRequest(bucketName, key, inputStream, DEFAULT_METADATA).withCannedAcl(CannedAccessControlList.PublicRead);
        s3ClientFactory.createClient().putObject(putObjectRequest);
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

    private static ObjectMetadata constructDefaultMetadata() {
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
        return objectMetadata;
    }
}
