package com.villa_agency.user;

import java.util.List;

public interface UserService {

	List<UserDto> getAllUsers();

	void createUser(User user);

	UserDto getUserById(Long id);
}
