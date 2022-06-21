package com.ivscheianu.common.blob.s3;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class S3Bucket {
    private final String namespace;
    private final String bucketName;
    private final String readingEnabled;
    private final String writingEnabled;
}
