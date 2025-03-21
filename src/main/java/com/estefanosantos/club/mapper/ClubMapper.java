package com.estefanosantos.club.mapper;

import java.util.stream.Collectors;

import com.estefanosantos.club.dto.ClubDto;
import com.estefanosantos.club.models.Club;

public class ClubMapper {
	
	public static Club mapToClub(ClubDto clubDto) {
		Club club = Club.builder()
				.id(clubDto.getId())
				.title(clubDto.getTitle())
				.photoUrl(clubDto.getPhotoUrl())
				.content(clubDto.getContent())
				.createdOn(clubDto.getCreatedOn())
				.updatedOn(clubDto.getUpdatedOn())
				.createdBy(clubDto.getCreatedBy())
				.build();
		return club;
	}
	
	public static ClubDto mapToClubDto(Club club) {
		ClubDto dto = ClubDto.builder()
			.id(club.getId())
			.title(club.getTitle())
			.photoUrl(club.getPhotoUrl())
			.content(club.getContent())
			.createdOn(club.getCreatedOn())
			.updatedOn(club.getUpdatedOn())
			.createdBy(club.getCreatedBy())
			.events(club.getEvents().stream().map((event) -> EventMapper.mapToEventDto(event)).collect(Collectors.toList()))
			.build();
		return dto;
	}
}
