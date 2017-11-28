package co.uk.app.commerce.unittest.users.registration.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.uk.app.commerce.unittest.users.base.AbstractUnitTest;
import co.uk.app.commerce.users.beans.RegistrationBean;
import co.uk.app.commerce.users.entity.Address;
import co.uk.app.commerce.users.entity.Users;
import co.uk.app.commerce.users.registration.service.RegistrationService;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationServiceUnitTest extends AbstractUnitTest {

	@Autowired
	private RegistrationService registrationService;

	@Test
	public void testPersistUsers() throws Exception {
		Users users = new Users();
		users.setUsername("abcd@test.com");
		users.setPassword("abcd1234");
		users.setRegistertype("R");
		users.setProfiletype("C");

		Address address = new Address();
		address.setAddresstype("S");
		address.setStatus("P");
		address.setTitle("Mr.");
		address.setFirstname("TestFirstname");
		address.setLastname("TestLastname");
		address.setEmail1("abcd@test.com");
		address.setNickname("nickname");
		address.setIsprimary(0);
		address.setSelfaddress(1);

		RegistrationBean registration = new RegistrationBean();
		registration.setUsers(users);
		registration.setAddress(address);

		RegistrationBean savedUser = registrationService.persist(registration);

		assertThat(savedUser.getUsers().getUserId()).isNotNull();
	}
}
