package com.ivscheianu.stockmanagement.user;

import com.ivscheianu.base.persistence.EntityRepository;
import com.ivscheianu.base.service.AbstractEntityService;
import com.ivscheianu.base.service.EntityMapper;
import com.ivscheianu.stockmanagement.auth.crypto.EncryptionChecker;
import com.ivscheianu.stockmanagement.bucket.BucketService;

import java.security.Principal;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.security.enterprise.SecurityContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Stateless
@Transactional
public class UserServiceImpl extends AbstractEntityService<Long, UserDTO, UserDO> implements UserService {

    private static final String UNKNOWN_PRINCIPAL = "Unable to extract principal";

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserMapper userMapper;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private EncryptionChecker encryptionChecker;

    @Inject
    private BucketService bucketService;

    @Override
    protected EntityRepository<Long, UserDO> getRepository() {
        return userRepository;
    }

    @Override
    protected EntityMapper<UserDTO, UserDO> getMapper() {
        return userMapper;
    }

    @Override
    public UserDTO getCurrentUser() {
        final Optional<String> principalName = extractPrincipalName();
        return principalName.map(name -> {
            final Optional<UserDO> userDO = userRepository.findByUsername(principalName.get());
            return mapToDto(userDO);
        }).orElseThrow(() -> new WebApplicationException(UNKNOWN_PRINCIPAL, Response.Status.UNAUTHORIZED));
    }

    @Override
    public UserDTO getByUsername(final String userName) {
        final Optional<UserDO> entityByUsername = userRepository.findByUsername(userName);
        return mapToDto(entityByUsername);
    }

    @Override
    public UserDTO save(final UserDTO user) {
        checkEmailValidity(user.getEmail());
        processSubmittedPassword(user);
        user.setBucket(bucketService.buildBucketForNewUser(user));
        return super.save(user);
    }

    private void checkEmailValidity(final String email) {
        try {
            new InternetAddress(email).validate();
        } catch (final AddressException addressException) {
            throw new WebApplicationException("Invalid email", Response.Status.BAD_REQUEST);
        }
    }

    private void processSubmittedPassword(final UserDTO userDto) {
        if (!encryptionChecker.isEncrypted(userDto)) {
            throw new WebApplicationException("Invalid password encryption", Response.Status.BAD_REQUEST);
        }
    }

    private Optional<String> extractPrincipalName() {
        return Optional.ofNullable(securityContext.getCallerPrincipal()).map(Principal::getName);
    }
}
