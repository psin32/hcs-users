package co.uk.app.commerce.users.beans;

import java.io.Serializable;

import co.uk.app.commerce.users.entity.Address;
import co.uk.app.commerce.users.entity.UserReg;
import co.uk.app.commerce.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationBean implements Serializable {

	private static final long serialVersionUID = -3232333029195336445L;

	private Users users;

	private UserReg userreg;

	private Address address;
}
