package co.uk.app.commerce.users.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.uk.app.commerce.users.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	Collection<Address> findByAddressId(String addressId);
}
