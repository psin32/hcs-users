package co.uk.app.commerce.users.security.filter;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.uk.app.commerce.users.config.SecurityConfiguration;
import co.uk.app.commerce.users.entity.Users;
import co.uk.app.commerce.users.security.service.TokenService;
import co.uk.app.commerce.users.security.service.TokenServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

	private SecurityConfiguration securityConfiguration;
	
	private TokenService tokenService;

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			Users creds = new ObjectMapper().readValue(req.getInputStream(), Users.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
					creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		if (securityConfiguration == null || tokenService == null) {
			ServletContext servletContext = req.getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			securityConfiguration = webApplicationContext.getBean(SecurityConfiguration.class);
			tokenService = webApplicationContext.getBean(TokenServiceImpl.class);
		}

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(securityConfiguration.getJwtSecret());
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		String token = Jwts.builder().setSubject(((User) auth.getPrincipal()).getUsername())
				.setExpiration(tokenService.generateExpirationDate()).signWith(signatureAlgorithm, signingKey)
				.compact();
		res.addCookie(new Cookie("token", token));
		res.addHeader(securityConfiguration.getJwtHeader(), securityConfiguration.getJwtTokenPrefix() + token);
	}
}
