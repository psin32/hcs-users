package co.uk.app.commerce.users.security;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.uk.app.commerce.users.entity.Users;
import co.uk.app.commerce.users.repository.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UsersRepository usersRepository;

	public UserDetailsServiceImpl(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = usersRepository.findByUsername(username);
		if (users == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(users.getUsername(), users.getPassword(), emptyList());
	}
}
