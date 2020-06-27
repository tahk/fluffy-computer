package gradle.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gradle.test.dto.UserDto;

@Repository
public interface UserDao extends JpaRepository<UserDto, Integer> {

}
