package com.estefanosantos.club.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.estefanosantos.club.dto.EventDto;
import com.estefanosantos.club.mapper.EventMapper;
import com.estefanosantos.club.models.Club;
import com.estefanosantos.club.models.Event;
import com.estefanosantos.club.repository.ClubRespository;
import com.estefanosantos.club.repository.EventRepository;
import com.estefanosantos.club.services.EventService;

@Service
public class EventServiceImpl implements EventService {
	
	private EventRepository eventRepository;
	
	private ClubRespository clubRespository;
	
	public EventServiceImpl(EventRepository eventRepository, ClubRespository clubRespository) {
		this.eventRepository = eventRepository;
		this.clubRespository = clubRespository;
	}
	
	@Override
	public void create(Long clubId, EventDto eventDto) {
		Club club = clubRespository.findById(clubId).get();
		Event event = EventMapper.mapToEvent(eventDto);
		event.setClub(club);
		eventRepository.save(event);
		
	}
	
	@Override
	public void update(EventDto eventDto) {
		Event event = EventMapper.mapToEvent(eventDto);
		eventRepository.save(event);
	}

	
	@Override
	public EventDto findByEventId(Long id) {
		Event event = eventRepository.findById(id).get();
		return EventMapper.mapToEventDto(event);
	}

	@Override
	public List<EventDto> findAllEvents() {
		List<Event> events = eventRepository.findAll();
		
		return events.stream().map((event) -> EventMapper.mapToEventDto(event)).collect(Collectors.toList());
	}

	@Override
	public void delete(Long id) {
		eventRepository.deleteById(id);
		
	}

	
	

}
