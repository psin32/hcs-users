package co.uk.app.commerce.users.registration.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.uk.app.commerce.users.beans.RegistrationBean;
import co.uk.app.commerce.users.beans.UserTokenBean;
import co.uk.app.commerce.users.config.SecurityConfiguration;
import co.uk.app.commerce.users.entity.Users;
import co.uk.app.commerce.users.registration.service.RegistrationService;
import co.uk.app.commerce.users.security.service.TokenService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

//	@Autowired
//	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private SecurityConfiguration securityConfiguration;

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Users> getAllUsers() {
		return registrationService.findAllUsers();
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> persistPerson(@RequestBody RegistrationBean registration, HttpServletResponse response) {
		if (registrationService.isValidUser(registration)) {
			RegistrationBean registeredUser = registrationService.persist(registration);
			String token = tokenService.generateToken(registeredUser.getUsers().getUsername());
			response.addHeader(securityConfiguration.getJwtHeader(), securityConfiguration.getJwtTokenPrefix() + token);
			return ResponseEntity.ok(registeredUser);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationBean authenticationBean,
//			HttpServletResponse response) throws AuthenticationException, IOException {
//
//		// Perform the security
//		final Authentication authentication = authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(authenticationBean.getUsername(),
//						authenticationBean.getPassword()));
//
//		// Inject into security context
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//
//		// token creation
//		User user = (User) authentication.getPrincipal();
//		String jws = tokenService.generateToken(user.getUsername());
//		int expiresIn = tokenService.getExpiredIn();
//
//		// Return the token
//		return ResponseEntity.ok(new UserTokenBean(jws, expiresIn));
//	}

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
	
    @RequestMapping({"/logout"})
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        (new SecurityContextLogoutHandler()).logout(request, null, null);
        return ResponseEntity.ok("Logout Success");
    }
}
