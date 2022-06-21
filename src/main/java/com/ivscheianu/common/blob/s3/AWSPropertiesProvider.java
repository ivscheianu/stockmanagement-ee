package com.ivscheianu.common.blob.s3;

import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.regions.Regions;

public interface AWSPropertiesProvider {
    AWSSessionCredentials getSessionCredentials();
    Regions getRegion();
}
