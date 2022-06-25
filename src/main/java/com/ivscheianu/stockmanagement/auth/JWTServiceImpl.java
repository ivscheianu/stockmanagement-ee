package com.ivscheianu.stockmanagement.auth;

import com.amazonaws.util.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class JWTServiceImpl implements JWTService {

    @Inject
    private SecurityPropertiesReader securityPropertiesReader;

    private String secretKey;

    private static final String AUTHORITIES_KEY = "auth";
    private static final long TOKEN_VALIDITY = TimeUnit.HOURS.toMillis(12);

    @PostConstruct
    private void postConstruct() {
        secretKey = securityPropertiesReader.getSecretKey();
    }

    @Override
    public String createToken(final String username, final Set<String> authorities) {
        final long now = new Date().getTime();
        return Jwts.builder()
                .setSubject(username)
                .claim(AUTHORITIES_KEY, String.join(StringUtils.COMMA_SEPARATOR, authorities))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(new Date(now + TOKEN_VALIDITY))
                .compact();
    }

    @Override
    public JWTCredential getCredential(final String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        final String[] split = claims.get(AUTHORITIES_KEY).toString().split(StringUtils.COMMA_SEPARATOR);
        final Set<String> authorities = Arrays.stream(split).collect(Collectors.toSet());
        return new JWTCredential(claims.getSubject(), authorities);
    }

    @Override
    public boolean isValidToken(final String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (final Exception exception) {
            log.error("Failed to validate token = {}", authToken, exception);
            return false;
        }
    }
}
