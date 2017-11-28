package co.uk.app.commerce.users.address.service;

import java.util.Set;

import co.uk.app.commerce.users.beans.AddressBean;
import co.uk.app.commerce.users.entity.Address;

public interface AddressService {

	Address getActiveAddressByAddressId(Long addressId);

	Address getActiveSelfAddressByUserId(Long userId);

	Set<Address> getActiveAddressesByUserId(Long userId);

	Set<Address> getActiveAddressesByUsername(String username);

	Address save(AddressBean addressBean);
}
