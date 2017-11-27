package co.uk.app.commerce.users.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.uk.app.commerce.users.entity.Users;
import co.uk.app.commerce.users.repository.AddressRepository;
import co.uk.app.commerce.users.repository.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UsersRepository usersRepository;
	
	private AddressRepository addressRepository;

	public UserDetailsServiceImpl(UsersRepository usersRepository, AddressRepository addressRepository) {
		this.usersRepository = usersRepository;
		this.addressRepository = addressRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = usersRepository.findByUsername(username);
		if (users == null) {
			throw new UsernameNotFoundException(username);
		}
		users.setAddress(addressRepository.findByUsersUserIdAndSelfaddressAndStatus(users.getUserId(), 1, "P"));
		return users;
	}
}
