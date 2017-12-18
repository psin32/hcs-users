package co.uk.app.commerce.users.registration.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import co.uk.app.commerce.users.beans.RegistrationBean;
import co.uk.app.commerce.users.entity.Address;
import co.uk.app.commerce.users.entity.UserReg;
import co.uk.app.commerce.users.entity.Users;
import co.uk.app.commerce.users.repository.AddressRepository;
import co.uk.app.commerce.users.repository.UserRegRepository;
import co.uk.app.commerce.users.repository.UsersRepository;

@Component
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRegRepository userRegRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Collection<Users> findAllUsers() {
		return usersRepository.findAll();
	}

	@Override
	public boolean isValidUser(RegistrationBean registration) {
		Users users = usersRepository.findByUsername(registration.getUsers().getUsername());
		if (null == users) {
			return true;
		}
		return false;
	}

	@Override
	public RegistrationBean persist(RegistrationBean registration) {
		Users users = usersRepository.save(registration.getUsers());
		
		registration.getUserreg().setUsers(users);
		String password = registration.getUserreg().getPassword();
		registration.getUserreg().setPassword(bCryptPasswordEncoder.encode(password));
		UserReg userReg = userRegRepository.save(registration.getUserreg());

		registration.getAddress().setUsers(users);
		Address address = addressRepository.save(registration.getAddress());

		RegistrationBean savedUser = new RegistrationBean();
		savedUser.setAddress(address);
		savedUser.setUsers(users);
		savedUser.setUserreg(userReg);
		return savedUser;
	}
}
