package com.villa_agency.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.villa_agency.user.User;
import com.villa_agency.user.UserDto;
import com.villa_agency.user.UserRepository;
import com.villa_agency.user.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepo;

	private ModelMapper modelMapper;

	public UserServiceImpl(UserRepository userRepo, ModelMapper modelMapper) {
		super();
		this.userRepo = userRepo;
		this.modelMapper = modelMapper;
	}


	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepo.findAll();
		// https://www.amitph.com/java-lists-modelmapper/
//		TypeToken<List<UserDto>> typeToken = new TypeToken<>() {
//		};
//		List<UserDto> userDtos = modelMapper.map(users, typeToken.getType());
//
//		return userDtos;

		List<UserDto> userDtos = new ArrayList<>();

		users.forEach(user -> userDtos.add(modelMapper.map(user, UserDto.class)));

		return userDtos;
	}

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
	}

	@Override
	public UserDto getUserById(Long id) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(id).get();

		UserDto userDto = this.modelMapper.map(user, UserDto.class);

		return userDto;
	}

}
