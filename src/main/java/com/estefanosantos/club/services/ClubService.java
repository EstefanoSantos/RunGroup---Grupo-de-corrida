package com.estefanosantos.club.services;

import java.util.List;

import com.estefanosantos.club.dto.ClubDto;

public interface ClubService {
	
	List<ClubDto> findAllClubs();
	
	void save(ClubDto clubDto);
	
	ClubDto findById(Long id);
	
	void update(ClubDto club);
	
	void delete(Long id);
	
	List<ClubDto> searchClubs(String query);

}
