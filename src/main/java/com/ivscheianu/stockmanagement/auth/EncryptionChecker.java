package com.ivscheianu.stockmanagement.auth;

import com.ivscheianu.stockmanagement.user.UserDTO;

public interface EncryptionChecker {
    /**
     * checks if the provided string is encrypted in a specific procedure
     * @param user the new registered user
     * @return true if it is encrypted in the desired way, otherwise false
     */
    boolean isEncrypted(final UserDTO user);
}
