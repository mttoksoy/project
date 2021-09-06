package SercurityJWT;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtil {

	private String SECRET_KEY="secret";
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	public Date extractExpiration (String token) {
		return (java.sql.Date) extractClaim(token, Claims::getExpiration);
	}
	public <T> T extractClaim(String token,Function<Claims, T> claimsResolver) {
		final Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new java.util.Date());
	}
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims =new HashMap<>();
		return createToken(claims,userDetails.getUsername());
	}
	
	public String createToken(Map<String, Object> claims, String subject) {
		
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new java.util.Date(System.currentTimeMillis()))
				.setExpiration(new java.util.Date(System.currentTimeMillis() +1000 *60 *60 *10)).signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public Boolean validateToken(String token , UserDetails userDetails) {
		final String usernameString=extractUsername(token);
		return (usernameString.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	
	
	
	
}
