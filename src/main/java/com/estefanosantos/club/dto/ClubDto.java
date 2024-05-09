package com.estefanosantos.club.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.estefanosantos.club.models.UserEntity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClubDto {
	
	private Long id;
	@NotEmpty(message = "Title cannot be empty")
	@NotNull(message = "Title cannot be null")
	private String title;
	@NotEmpty(message = "We need a photo url")
	@NotNull(message = "We need a photo url")
	private String photoUrl;
	@NotEmpty(message = "Please tell us about your club.")
	@NotNull(message = "Please tell us about your club.")
	private String content;
	
	private LocalDateTime createdOn;

	private LocalDateTime updatedOn;
	
	private UserEntity createdBy;
	
	List<EventDto> events;
}
