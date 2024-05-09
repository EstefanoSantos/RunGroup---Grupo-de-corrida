package com.estefanosantos.club.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.estefanosantos.club.models.Club;

public interface ClubRespository extends JpaRepository<Club, Long> {
	
	Optional<Club> findByTitle(String title);
	
	@Query("SELECT c FROM Club c WHERE c.title LIKE CONCAT('%', :query, '%')")
	List<Club> searchClubs(String query);
			

}
