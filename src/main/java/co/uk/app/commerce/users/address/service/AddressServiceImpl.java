package co.uk.app.commerce.users.address.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.uk.app.commerce.users.beans.AddressBean;
import co.uk.app.commerce.users.entity.Address;
import co.uk.app.commerce.users.repository.AddressRepository;
import co.uk.app.commerce.users.repository.UsersRepository;

@Component
public class AddressServiceImpl implements AddressService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Address getActiveAddressByAddressId(Long addressId) {
		return addressRepository.findByAddressIdAndStatus(addressId, "P");
	}

	@Override
	public Set<Address> getActiveAddressesByUserId(Long userId) {
		return addressRepository.findByUsersUserIdAndSelfaddressAndStatus(userId, 0, "P");
	}

	@Override
	public Set<Address> getActiveSelfAddressByUserId(Long userId) {
		return addressRepository.findByUsersUserIdAndSelfaddressAndStatus(userId, 1, "P");
	}

	@Override
	public Set<Address> getActiveAddressesByUsername(String username) {
		return addressRepository.findByUsersUsernameAndStatus(username, "P");
	}

	@Override
	public Address save(AddressBean addressBean) {
		Long usersId = addressBean.getUsersId();
		System.out.println("USERS ID ====== "+usersId);
		Address address = addressBean.getAddress();
		address.setUsers(usersRepository.findByUserId(usersId));
		return addressRepository.save(address);
	}

}
