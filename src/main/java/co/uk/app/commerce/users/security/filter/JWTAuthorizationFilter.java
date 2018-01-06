package co.uk.app.commerce.users.security.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import co.uk.app.commerce.users.config.SecurityConfiguration;
import co.uk.app.commerce.users.security.service.TokenService;
import co.uk.app.commerce.users.security.service.TokenServiceImpl;
import io.jsonwebtoken.Claims;

@Component
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private SecurityConfiguration securityConfiguration;

	private TokenService tokenService;

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		if (securityConfiguration == null) {
			ServletContext servletContext = req.getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			securityConfiguration = webApplicationContext.getBean(SecurityConfiguration.class);
		}
		String header = req.getHeader(securityConfiguration.getJwtHeader());

		if (header == null || !header.startsWith(securityConfiguration.getJwtTokenPrefix())) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		if (tokenService == null) {
			ServletContext servletContext = request.getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			tokenService = webApplicationContext.getBean(TokenServiceImpl.class);
		}
		String token = tokenService.getToken(request);
		if (token != null) {

			Claims claims = tokenService.getAllClaimsFromToken(token);

			if (null != claims) {
				String user = claims.getSubject();

				if (user != null) {
					request.setAttribute("USER_ID", claims.get("userId"));
					request.setAttribute("REGISTER_TYPE", claims.get("registertype"));
					String registerType = String.valueOf(claims.get("registertype"));
					if (null != registerType && registerType.equalsIgnoreCase("G")) {
						return null;
					}
					return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
				}
			}
			return null;
		}
		return null;
	}
}
