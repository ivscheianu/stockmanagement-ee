package com.ivscheianu.common.blob;

import java.io.InputStream;

public interface BlobStorageBroker {
    void upload(final String bucketName, final String key, final InputStream inputStream);
    void createBucket(final String bucketName);
    String getLink(final String bucketName, final String key);
}
