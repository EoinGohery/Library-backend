package CS4125.group5.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtProvider {
    private String SECRET_KEY = "secret123456789987654321123456789987654321123456789987654321";
    @Value("${jwt.expiration.time}")
    private long jwtExpirationinMillis;

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails){
        return createToken(userDetails.getUsername());
    }
    private String createToken(String subject){
        return Jwts.builder().setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationinMillis)))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }
    public String createTokenWithUsername(String username){
        return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationinMillis)))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public long getJwtExpirationinMillis() {
        return jwtExpirationinMillis;
    }


}