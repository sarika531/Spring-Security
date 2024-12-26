package com.demo.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.Entity.User;
import com.demo.Payload.UserDto;
import com.demo.Repository.UserRepository;
import com.demo.Service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passEncoder;
	@Override
	public UserDto createUser(UserDto userDto)
	{
		userDto.setPassword(passEncoder.encode(userDto.getPassword()));
		//userDto is not an entity of user
		User user = Usertoentity(userDto);
		  User savedUser = userRepository.save(user); 
		return entityUserDto(savedUser);
	}
    
	private User Usertoentity(UserDto userDto) {
		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
	   return user;
	}
	
	private UserDto entityUserDto(User savedUser) {
	  UserDto userDto = new UserDto();
	  userDto.setId(savedUser.getId());
	  userDto.setEmail(savedUser.getEmail());
	  userDto.setName(savedUser.getName());
	  userDto.setPassword(savedUser.getPassword());
	  return userDto;
	}
}