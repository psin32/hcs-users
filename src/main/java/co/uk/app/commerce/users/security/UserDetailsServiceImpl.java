package co.uk.app.commerce.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.uk.app.commerce.users.beans.LoginBean;
import co.uk.app.commerce.users.entity.UserReg;
import co.uk.app.commerce.users.entity.Users;
import co.uk.app.commerce.users.repository.AddressRepository;
import co.uk.app.commerce.users.repository.UserRegRepository;
import co.uk.app.commerce.users.repository.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRegRepository userRegRepository;

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

		UserReg userReg = userRegRepository.findByUsersUserId(users.getUserId());

		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(users.getUsername());
		loginBean.setPassword(userReg.getPassword());
		loginBean.setUsers(users);

		return loginBean;
	}
}
