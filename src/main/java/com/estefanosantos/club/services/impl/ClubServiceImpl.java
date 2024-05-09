package com.estefanosantos.club.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.estefanosantos.club.dto.ClubDto;
import com.estefanosantos.club.mapper.ClubMapper;
import com.estefanosantos.club.models.Club;
import com.estefanosantos.club.models.UserEntity;
import com.estefanosantos.club.repository.ClubRespository;
import com.estefanosantos.club.repository.UserRepository;
import com.estefanosantos.club.security.SecurityUtil;
import com.estefanosantos.club.services.ClubService;

@Service
public class ClubServiceImpl implements ClubService{
	
	private ClubRespository clubRespository;
	private UserRepository userRepository;
	
	public ClubServiceImpl(ClubRespository clubRespository, UserRepository userRepository) {
		this.clubRespository = clubRespository;
		this.userRepository = userRepository;
	}
	
	@Override
	public void save(ClubDto clubDto) {
		String username = SecurityUtil.getUserSession();
		UserEntity user = userRepository.findByUsername(username);
		Club club = ClubMapper.mapToClub(clubDto);
		club.setCreatedBy(user);
		clubRespository.save(club);	
	}
	
	@Override
	public void update(ClubDto clubDto) {
		String username = SecurityUtil.getUserSession();
		UserEntity user = userRepository.findByUsername(username);
		Club club = ClubMapper.mapToClub(clubDto);
		club.setCreatedBy(user);
		clubRespository.save(club);
	}
	
	@Override
	public ClubDto findById(Long clubId) {
		Club club = clubRespository.findById(clubId).get();
		return ClubMapper.mapToClubDto(club);
	}
	
	@Override
	public List<ClubDto> findAllClubs() {
		List<Club> clubs = clubRespository.findAll();
		
		return clubs.stream().map((club) -> ClubMapper.mapToClubDto(club)).collect(Collectors.toList());
		
	}

	@Override
	public void delete(Long id) {
		clubRespository.deleteById(id);
	}

	@Override
	public List<ClubDto> searchClubs(String query) {
		List<Club> clubs = clubRespository.searchClubs(query);
		List<ClubDto> dtos = clubs.stream().map((club) -> ClubMapper.mapToClubDto(club)).collect(Collectors.toList());
		return dtos;
	}

	

	

	



	

}
