package co.uk.app.commerce.users.address.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.uk.app.commerce.users.beans.AddressBean;
import co.uk.app.commerce.users.constant.UsersConstant;
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
	public Address getActiveSelfAddressByUserId(Long userId) {
		Set<Address> addressSet = addressRepository.findByUsersUserIdAndSelfaddressAndStatus(userId, 1, "P");
		Address address = null;
		while (addressSet.iterator().hasNext()) {
			address = addressSet.iterator().next();
			break;
		}
		return address;
	}

	@Override
	public Set<Address> getActiveAddressesByUsername(String username) {
		return addressRepository.findByUsersUsernameAndStatus(username, "P");
	}

	@Override
	public Address save(AddressBean addressBean) {
		Long usersId = addressBean.getUsersId();
		Address address = addressBean.getAddress();
		address.setUsers(usersRepository.findByUserId(usersId));
		return addressRepository.save(address);
	}

	@Override
	public List<Address> getActiveShippingAddresses(Long usersId) {
		return addressRepository
				.findByUsersUserIdAndStatusAndSelfaddress(usersId, UsersConstant.ADDRESS_ACTIVE_STATUS,
						UsersConstant.NON_SELFADDRESS)
				.stream().filter(address -> address.getAddresstype().contains(UsersConstant.ADDRESS_SHIPPING_TYPE))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteAddress(Long userId, Long addressId) {
		Address address = addressRepository.findByUsersUserIdAndAddressId(userId, addressId);
		address.setStatus("T");
		addressRepository.save(address);
	}
}
