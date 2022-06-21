package com.ivscheianu.stockmanagement.auth.crypto;

import com.ivscheianu.stockmanagement.user.UserDTO;

import java.util.regex.Pattern;

import javax.ejb.Stateless;

@Stateless
public class BCryptChecker implements EncryptionChecker {

    private final Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    @Override
    public boolean isEncrypted(final UserDTO user) {
        return BCRYPT_PATTERN.matcher(user.getPassword()).matches();
    }
}