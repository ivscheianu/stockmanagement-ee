package com.ivscheianu.common.property;

import static java.util.Objects.requireNonNull;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractPropertiesReader implements PropertiesReader {

    private static final ClassLoader CLASS_LOADER = AbstractPropertiesReader.class.getClassLoader();

    protected abstract String getPropertiesFileName();

    @Override
    public String readProperty(final String propertyName) {
        return loadProperties().getProperty(propertyName);
    }

    @Override
    public Map<String, String> readProperties(final Set<String> properties) {
        final Properties loadProperties = loadProperties();
        return properties
            .stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(
                key -> key,
                key -> loadProperties.getProperty(key, StringUtils.EMPTY)
            ));
    }

    @Override
    @SneakyThrows
    public Properties loadProperties() {
        final String path = requireNonNull(CLASS_LOADER.getResource(getPropertiesFileName())).getFile();
        try (final InputStream fileInputStream = new FileInputStream(path)) {
            final Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        }
    }
}
