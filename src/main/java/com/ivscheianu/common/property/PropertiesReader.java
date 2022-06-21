package com.ivscheianu.common.property;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

public interface PropertiesReader {
    Properties loadProperties();
    String readProperty(final String propertyName);
    Map<String, String> readProperties(final Set<String> properties);
}
