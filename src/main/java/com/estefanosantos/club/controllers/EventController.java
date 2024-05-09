package com.estefanosantos.club.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.estefanosantos.club.dto.EventDto;
import com.estefanosantos.club.models.Event;
import com.estefanosantos.club.models.UserEntity;
import com.estefanosantos.club.security.SecurityUtil;
import com.estefanosantos.club.services.EventService;
import com.estefanosantos.club.services.UserService;

import jakarta.validation.Valid;

@Controller
public class EventController {
	
	@Autowired
	private EventService eventService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/events")
	public String listEvents(Model model) {
		UserEntity user = new UserEntity();
		List<EventDto> events = eventService.findAllEvents();
		String username = SecurityUtil.getUserSession();
		
		if (username != null) {
			user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		model.addAttribute("user", user);
		model.addAttribute("events", events);
		return "events-list";
	}
	
	@GetMapping("/events/{eventId}")
	public String eventDetail(@PathVariable("eventId") Long eventId, Model model) {
		UserEntity user = new UserEntity();
		EventDto eventDto = eventService.findByEventId(eventId);
		String username = SecurityUtil.getUserSession();
		
		if (username != null) {
			user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		model.addAttribute("club", eventDto.getClub());
		model.addAttribute("user", user);
		model.addAttribute("event", eventDto);
		return "events-detail";
	}
	
	@GetMapping("/events/{clubId}/new")
	public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
		Event event = new Event();
		model.addAttribute("clubId", clubId);
		model.addAttribute("event", event);
		return "events-create";
	}
	
	@PostMapping("events/{clubId}")
	public String createEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("event") EventDto eventDto, Model model) {
		eventService.create(clubId, eventDto);
		return "redirect:/clubs/" + clubId;
	}
	
	@GetMapping("events/{eventId}/edit")
	public String editClubForm(@PathVariable("eventId") Long eventId, Model model) {
		EventDto event = eventService.findByEventId(eventId);
		model.addAttribute("event", event);
		return "events-edit";
	}
	
	@PostMapping("events/{eventId}/edit")
	public String updateEvent(@PathVariable("eventId") Long id, @Valid @ModelAttribute("event") EventDto event, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("event", event);
			return "events-edit";
		}
		EventDto eventDto = eventService.findByEventId(id);
		event.setId(id);
		event.setClub(eventDto.getClub());
		eventService.update(event);
		return "redirect:/events";
	}
	
	@GetMapping("/events/{eventId}/delete") 
	public String deleteEvent(@PathVariable("eventId") Long id) {
		eventService.delete(id);
		return "redirect:/events";
	}

}
