package gradle.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gradle.test.entity.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByUserId(String userId);


}
