package com.estefanosantos.club.services;

import java.util.List;

import com.estefanosantos.club.dto.EventDto;

public interface EventService {
	
	void create(Long clubId, EventDto eventDto);
	
	List<EventDto> findAllEvents();
	
	EventDto findByEventId(Long id);

	void update(EventDto eventDto);
	
	void delete(Long id);
}
