package co.uk.app.commerce.users.security.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetails;

import co.uk.app.commerce.users.entity.Users;
import io.jsonwebtoken.Claims;

public interface TokenService {

	public String getUsernameFromToken(String token);

	public Date getIssuedAtDateFromToken(String token);

	public String getAudienceFromToken(String token);

	public String refreshToken(String token);

	public String generateToken(Users users);

	public Claims getAllClaimsFromToken(String token);

	public Date generateExpirationDate();

	public int getExpiredIn();

	public Boolean validateToken(String token, UserDetails userDetails);

	public Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset);

	public String getToken(HttpServletRequest request);

	public String getAuthHeaderFromHeader(HttpServletRequest request);
}
