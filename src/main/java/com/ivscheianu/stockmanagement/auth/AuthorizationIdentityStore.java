package com.ivscheianu.stockmanagement.auth;

import static javax.security.enterprise.identitystore.IdentityStore.ValidationType.PROVIDE_GROUPS;

import com.ivscheianu.stockmanagement.role.RoleService;
import org.apache.commons.collections4.SetUtils;

import java.util.Collections;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

@RequestScoped
public class AuthorizationIdentityStore implements IdentityStore {

    @Inject
    private RoleService roleService;

    @Override
    public Set<String> getCallerGroups(final CredentialValidationResult validationResult) {
        final String principal = validationResult.getCallerPrincipal().getName();
        final Set<String> stringRoles = roleService.getUserRolesAsString(principal);
        return SetUtils.emptyIfNull(stringRoles);
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return Collections.singleton(PROVIDE_GROUPS);
    }
}
