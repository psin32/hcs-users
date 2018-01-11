package co.uk.app.commerce.users.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@PropertySource("classpath:application.properties")
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
}
