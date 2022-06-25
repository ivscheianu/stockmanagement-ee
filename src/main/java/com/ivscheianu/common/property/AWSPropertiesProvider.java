package com.ivscheianu.common.property;

import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.regions.Regions;

public interface AWSPropertiesProvider {
    AWSSessionCredentials getSessionCredentials();
    Regions getRegion();
}
