package co.uk.app.commerce.users.beans;

import co.uk.app.commerce.users.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressBean {

	private Long usersId;

	private Address address;
}
