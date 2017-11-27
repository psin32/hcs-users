package co.uk.app.commerce.users.beans;

import co.uk.app.commerce.users.entity.Address;

public class AddressBean {

	private Long usersId;

	private Address address;

	public Long getUsersId() {
		return usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
