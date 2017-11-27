package co.uk.app.commerce.users.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import co.uk.app.commerce.users.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	Address findByAddressIdAndStatus(Long addressId, String status);

	Set<Address> findByUsersUserIdAndStatus(Long userId, String status);
	
	Set<Address> findByUsersUsernameAndStatus(String username, String status);

	Set<Address> findByUsersUserIdAndSelfaddressAndStatus(Long userId, Integer selfaddress, String status);
}
