package gradle.test.service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gradle.test.dto.UserDto;
import gradle.test.model.User;
import gradle.test.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public Optional<User> findUserById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		if (user == null) {
			System.out.println("User with id = " + id + " doesn't exist. ");
		}
		return user;
	}

	@Transactional
	public User createUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		entityManager.persist(user);
		return userRepository.save(user);
	}

	@Transactional
	public User updateUserBasicInfo(UserDto userDto) {
		User user = findUserById(userDto.getId()).get();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setSex(userDto.getSex());
		return user;
	}

	@Transactional
	public User updateUserInfo(UserDto userDto) {
		User user = findUserById(userDto.getId()).get();
		user.setUserId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		return user;
	}

	@Transactional
	public void deleteUser(Integer id) {
		User user = findUserById(id).get();
		userRepository.delete(user);
	}

}
