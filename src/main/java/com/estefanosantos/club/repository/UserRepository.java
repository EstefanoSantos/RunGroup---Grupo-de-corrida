package com.estefanosantos.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estefanosantos.club.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);
		
	UserEntity findByUsername(String username);
	
	UserEntity findFirstByUsername(String username);

}
