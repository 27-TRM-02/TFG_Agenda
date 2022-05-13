package trm.agenda.authentication.utility;

import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtility {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long validity;

    public String getUsernameFromToken(String token) {
        return this.getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return this.getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return this.getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return this.validateToken(token) && this.getExpirationDateFromToken(token).before(new Date());
    }

    private Boolean ignoreTokenExpiration(String token) {
        return false;
    }

    public String generateToken(UserDetails userDetails) {
        return this.doGenerateToken(userDetails.getUsername());
    }

    private String doGenerateToken(String subject) {
        Long currentTime = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + this.validity))
                .signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }

    public Boolean canTokenBeRefreshed(String token) {
        return (!this.isTokenExpired(token) || this.ignoreTokenExpiration(token));
    }

    public Boolean validateToken(String token) {
        Boolean tokenIsValid = false;
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            tokenIsValid = true;
        } catch (SignatureException e) {
        } catch (MalformedJwtException e) {
        } catch (ExpiredJwtException e) {
        } catch (UnsupportedJwtException e) {
        } catch (IllegalArgumentException e) {
        }
        return tokenIsValid;
    }

    public Boolean tokenIsValid(String token) {
        return !this.isTokenExpired(token) && this.validateToken(token);
    }

    public Boolean tokenIsValid(HttpServletRequest request) {
        Boolean tokenIsValid = false;
        String token = this.extractJwtFromRequest(request);
        if (Objects.nonNull(token)) {
            tokenIsValid = this.tokenIsValid(token);
        }
        return tokenIsValid;
    }

    public String extractJwtFromRequest(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        return StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")
                ? headerAuth.substring(7, headerAuth.length())
                : null;
    }
}