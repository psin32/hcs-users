package co.uk.app.commerce.users.beans;

import java.io.Serializable;

import co.uk.app.commerce.users.entity.Address;
import co.uk.app.commerce.users.entity.UserReg;
import co.uk.app.commerce.users.entity.Users;

public class RegistrationBean implements Serializable {

	private static final long serialVersionUID = -3232333029195336445L;

	private Users users;

	private UserReg userreg;

	private Address address;

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public UserReg getUserreg() {
		return userreg;
	}

	public void setUserreg(UserReg userreg) {
		this.userreg = userreg;
	}

}
