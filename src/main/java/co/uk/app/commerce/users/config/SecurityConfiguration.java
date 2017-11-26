package co.uk.app.commerce.users.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class SecurityConfiguration {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration.time}")
	private int jwtExpirationTime;

	@Value("${jwt.token.prefix}")
	private String jwtTokenPrefix;

	@Value("${jwt.header}")
	private String jwtHeader;

	@Value("${jwt.signup.url}")
	private String jwtSignupUrl;

	@Value("${jwt.signin.url}")
	private String jwtSigninUrl;

	@Value("${jwt.audience}")
	private String jwtAudience;

	public String getJwtSecret() {
		return jwtSecret;
	}

	public int getJwtExpirationTime() {
		return jwtExpirationTime;
	}

	public String getJwtTokenPrefix() {
		return jwtTokenPrefix;
	}

	public String getJwtHeader() {
		return jwtHeader;
	}

	public String getJwtSignupUrl() {
		return jwtSignupUrl;
	}

	public String getJwtSigninUrl() {
		return jwtSigninUrl;
	}

	public String getJwtAudience() {
		return jwtAudience;
	}
}
