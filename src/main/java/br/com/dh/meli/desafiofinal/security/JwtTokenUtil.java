package br.com.dh.meli.desafiofinal.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * The type Jwt token util.
 */
@Log4j2
@Component
public class JwtTokenUtil {

    private static final long TOKEN_EXPIRATION = 600_000; // 10 min
    public static final long REFRESH_TOKEN_EXPIRATION = 7 * 86_400_000; // 7 days
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Generate access token string.
     *
     * @param request the request
     * @param user    the user
     * @return the string
     */
    public String generateAccessToken(HttpServletRequest request, UserPrincipal user) {
        String accessToken = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer(request.getRequestURL().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .signWith(SECRET_KEY)
                .compact();
        return accessToken;

    }

    /**
     * Generate refresh token string.
     *
     * @param request the request
     * @param user    the user
     * @return the string
     */
    public String generateRefreshToken(HttpServletRequest request, UserPrincipal user) {
        String refreshToken = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer(request.getRequestURL().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(SECRET_KEY)
                .compact();
        return refreshToken;
    }

    /**
     * Validate jwt token boolean.
     *
     * @param authToken the auth token
     * @return the boolean
     */
    public boolean validateJwtToken(String authToken)  {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token", ex);
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token", ex);
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token", ex);
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.", ex);
        }
        return false;
    }

    /**
     * Gets subject.
     *
     * @param token the token
     * @return the subject
     */
    public String getSubject(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
