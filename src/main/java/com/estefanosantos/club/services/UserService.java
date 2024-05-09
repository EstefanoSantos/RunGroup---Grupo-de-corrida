package com.estefanosantos.club.services;

import com.estefanosantos.club.dto.RegistrationDto;
import com.estefanosantos.club.models.UserEntity;

public interface UserService {
	
	void create(RegistrationDto registrationDto);

	UserEntity findByUsername(String username);

	UserEntity findByEmail(String email);

}
