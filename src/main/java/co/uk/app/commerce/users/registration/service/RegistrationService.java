package co.uk.app.commerce.users.registration.service;

import java.util.Collection;

import co.uk.app.commerce.users.beans.RegistrationBean;
import co.uk.app.commerce.users.entity.Users;

public interface RegistrationService {

	Collection<Users> findAllUsers();
	
	boolean isValidUser(RegistrationBean registration);
	
	RegistrationBean persist(RegistrationBean registration);
}
