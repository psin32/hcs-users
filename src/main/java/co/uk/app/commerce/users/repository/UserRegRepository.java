package co.uk.app.commerce.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.uk.app.commerce.users.entity.UserReg;

public interface UserRegRepository extends JpaRepository<UserReg, Long> {

	UserReg findByUsersUserId(Long userId);
}
