package gradle.test.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import gradle.test.dto.UserDto;
import gradle.test.model.User;
import gradle.test.repository.UserRepository;

public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public User getUserById(Integer id) {
		User user = entityManager.find(User.class, id);
		if (user == null) {
			System.out.println("User with id = " + id + " doesn't exist. ");
		}
		return user;
	}

	@Transactional
	public User createUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		entityManager.persist(user);
		return user;
	}

	@Transactional
	public User updateUserBasicInfo(UserDto userDto) {
		User user = getUserById(userDto.getId());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setSex(userDto.getSex());
		return user;
	}

	@Transactional
	public User updateUserInfo(UserDto userDto) {
		User user = getUserById(userDto.getId());
		user.setUserId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		return user;
	}

	@Transactional
	public void deleteUser(Integer id) {
		User user = getUserById(id);
		entityManager.remove(user);
	}


}
