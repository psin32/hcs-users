package co.uk.app.commerce.users.application;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import co.uk.app.commerce.users.beans.Role;
import co.uk.app.commerce.users.entity.Address;
import co.uk.app.commerce.users.entity.UserReg;
import co.uk.app.commerce.users.entity.Users;
import co.uk.app.commerce.users.repository.AddressRepository;
import co.uk.app.commerce.users.repository.UserRegRepository;
import co.uk.app.commerce.users.repository.UsersRepository;

@Component
public class UsersInitialDataLoader implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsersInitialDataLoader.class);

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private UserRegRepository userRegRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public void run(String... arg0) throws Exception {
		LOGGER.info("Setting up initial data....");

		String emailId = "hcsadmin@gmail.com";
		Long userId = null;

		Users adminUser = usersRepository.findByUsername(emailId);
		if (null == adminUser) {
			adminUser = new Users();
			adminUser.setUsername(emailId);
			adminUser.setRole(Role.ADMIN);
			adminUser.setProfiletype("C");
			adminUser.setRegistertype("R");
			Users savedUsers = usersRepository.save(adminUser);
			userId = savedUsers.getUserId();
		} else {
			userId = adminUser.getUserId();
		}

		UserReg adminUserReg = userRegRepository.findByUsersUserId(userId);
		if (null == adminUserReg) {
			adminUserReg = new UserReg();
			adminUserReg.setPassword(bCryptPasswordEncoder.encode("admin123"));
			adminUserReg.setUsers(adminUser);
			userRegRepository.save(adminUserReg);
		}

		Set<Address> addressSet = addressRepository.findByUsersUserIdAndSelfaddressAndStatus(userId, 1, "P");

		Address adminAddress = addressSet.stream().findFirst().orElse(null);
		if (null == adminAddress) {
			adminAddress = new Address();
			adminAddress.setEmail1(emailId);
			adminAddress.setFirstname("Prashant");
			adminAddress.setLastname("Singh");
			adminAddress.setTitle("Mr.");
			adminAddress.setFirstname("Prashant");
			adminAddress.setIsprimary(0);
			adminAddress.setSelfaddress(1);
			adminAddress.setAddresstype("S");
			adminAddress.setStatus("P");
			adminAddress.setUsers(adminUser);
			addressRepository.save(adminAddress);
		}
	}

}
