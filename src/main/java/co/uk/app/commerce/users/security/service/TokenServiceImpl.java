package co.uk.app.commerce.users.security.service;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import co.uk.app.commerce.users.config.SecurityConfiguration;
import co.uk.app.commerce.users.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenServiceImpl implements TokenService {

	@Autowired
	private SecurityConfiguration securityConfiguration;

	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	public Date getIssuedAtDateFromToken(String token) {
		Date issueAt;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			issueAt = claims.getIssuedAt();
		} catch (Exception e) {
			issueAt = null;
		}
		return issueAt;
	}

	public String getAudienceFromToken(String token) {
		String audience;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			audience = claims.getAudience();
		} catch (Exception e) {
			audience = null;
		}
		return audience;
	}

	public String refreshToken(String token) {
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(securityConfiguration.getJwtSecret());
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		String refreshedToken;
		Date a = new Date();
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			claims.setIssuedAt(a);
			refreshedToken = Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
					.signWith(signatureAlgorithm, signingKey).compact();
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}

	public String generateToken(String username) {
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(securityConfiguration.getJwtSecret());
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		String audience = securityConfiguration.getJwtAudience();
		return Jwts.builder().setId(username).setIssuer("commerce").setSubject(username).setAudience(audience)
				.setIssuedAt(new Date()).setExpiration(generateExpirationDate())
				.signWith(signatureAlgorithm, signingKey).compact();
	}

	public Claims getAllClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(securityConfiguration.getJwtSecret()))
					.parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	public Date generateExpirationDate() {
		long expiresIn = getExpiredIn();
		Date date = new Date();
		return new Date(date.getTime() + expiresIn * 1000);
	}

	public int getExpiredIn() {
		return securityConfiguration.getJwtExpirationTime();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		Users users = (Users) userDetails;
		final String username = getUsernameFromToken(token);
		final Date created = getIssuedAtDateFromToken(token);
		return (username != null && username.equals(userDetails.getUsername())
				&& !isCreatedBeforeLastPasswordReset(created, users.getLastPasswordResetDate()));
	}

	public Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}

	public String getToken(HttpServletRequest request) {
		String authHeader = getAuthHeaderFromHeader(request);
		if (authHeader != null && authHeader.startsWith(securityConfiguration.getJwtTokenPrefix())) {
			return authHeader.replace(securityConfiguration.getJwtTokenPrefix(), "");
		}
		return null;
	}

	public String getAuthHeaderFromHeader(HttpServletRequest request) {
		return request.getHeader(securityConfiguration.getJwtHeader());
	}
}
