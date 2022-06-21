package com.ivscheianu.stockmanagement.auth;

import com.ivscheianu.common.property.AbstractPropertiesReader;
import com.ivscheianu.stockmanagement.auth.crypto.SecurityPropertiesReader;

import javax.ejb.Stateless;

@Stateless
public class SecurityPropertiesReaderImpl extends AbstractPropertiesReader implements SecurityPropertiesReader {

    private static final String CREDENTIALS_FILE = "security.properties";
    private static final String SECRET_KEY = "secret_key";

    @Override
    protected String getPropertiesFileName() {
        return CREDENTIALS_FILE;
    }

    @Override
    public String getSecretKey() {
        return readProperty(SECRET_KEY);
    }
}
