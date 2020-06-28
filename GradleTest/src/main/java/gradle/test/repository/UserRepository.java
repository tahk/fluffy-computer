package gradle.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gradle.test.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
