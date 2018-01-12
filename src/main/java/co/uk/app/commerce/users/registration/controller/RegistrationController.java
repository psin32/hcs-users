package co.uk.app.commerce.users.registration.controller;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.uk.app.commerce.users.beans.RegistrationBean;
import co.uk.app.commerce.users.beans.UserTokenBean;
import co.uk.app.commerce.users.registration.service.RegistrationService;
import co.uk.app.commerce.users.security.service.TokenService;

@RestController
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private TokenService tokenService;

	@PostMapping(path = "/register")
	public ResponseEntity<?> persistPerson(@RequestBody RegistrationBean registration, HttpServletResponse response) {
		if (registrationService.isValidUser(registration)) {
			RegistrationBean registeredUser = registrationService.persist(registration);
			String token = tokenService.generateToken(registeredUser.getUsers());

			Cookie cookie = new Cookie("TOKEN", token);
			cookie.setPath("/");
			response.addCookie(cookie);

			cookie = new Cookie("USERNAME", registeredUser.getAddress().getFirstname());
			cookie.setPath("/");
			response.addCookie(cookie);

			cookie = new Cookie("REGISTER_TYPE", "R");
			cookie.setPath("/");
			response.addCookie(cookie);
			return ResponseEntity.ok(registeredUser);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request, HttpServletResponse response,
			Principal principal) {

		String authToken = tokenService.getToken(request);

		if (authToken != null && principal != null) {

			// TODO check user password last update
			String refreshedToken = tokenService.refreshToken(authToken);
			int expiresIn = tokenService.getExpiredIn();

			return ResponseEntity.ok(new UserTokenBean(refreshedToken, expiresIn));
		} else {
			UserTokenBean userTokenState = new UserTokenBean();
			return ResponseEntity.accepted().body(userTokenState);
		}
	}
}
