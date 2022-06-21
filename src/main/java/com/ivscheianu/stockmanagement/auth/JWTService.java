package com.ivscheianu.stockmanagement.auth;

import com.amazonaws.util.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class JWTService {

    private String secretKey;

    private static final String AUTHORITIES_KEY = "auth";
    private static final long TOKEN_VALIDITY = TimeUnit.HOURS.toMillis(12);

    @PostConstruct
    private void postConstruct() {
        secretKey = "my-secret-jwt-key";
    }

    public String createToken(final String username, final Set<String> authorities) {
        final long now = new Date().getTime();
        return Jwts.builder()
                .setSubject(username)
                .claim(AUTHORITIES_KEY, String.join(StringUtils.COMMA_SEPARATOR, authorities))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(new Date(now + TOKEN_VALIDITY))
                .compact();
    }

    public JWTCredential getCredential(final String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        final Set<String> authorities = new HashSet<>(Arrays.asList(claims.get(AUTHORITIES_KEY).toString().split(StringUtils.COMMA_SEPARATOR)));
        return new JWTCredential(claims.getSubject(), authorities);
    }

    public boolean validateToken(final String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (final Exception exception) {
            log.error("Failed to validate token = {}", authToken, exception);
            return false;
        }
    }
}
