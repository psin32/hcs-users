package co.uk.app.commerce.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.uk.app.commerce.users.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	Users findByUsername(String username);
}
