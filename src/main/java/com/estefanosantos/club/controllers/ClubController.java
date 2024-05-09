package com.estefanosantos.club.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estefanosantos.club.dto.ClubDto;
import com.estefanosantos.club.models.Club;
import com.estefanosantos.club.models.UserEntity;
import com.estefanosantos.club.security.SecurityUtil;
import com.estefanosantos.club.services.UserService;
import com.estefanosantos.club.services.impl.ClubServiceImpl;

import jakarta.validation.Valid;

@Controller
public class ClubController {

	private ClubServiceImpl clubService;
	private UserService userService;

	public ClubController(ClubServiceImpl clubService, UserService userService) {
		this.clubService = clubService;
		this.userService = userService;
	}

	@GetMapping("/clubs")
	public String listClubs(Model model) {
		UserEntity user = new UserEntity();
		List<ClubDto> clubs = clubService.findAllClubs();
		String username = SecurityUtil.getUserSession();

		if (username != null) {
			user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		model.addAttribute("user", user);
		model.addAttribute("clubs", clubs);
		return "clubs-list";
	}

	@GetMapping("/clubs/new")
	public String createClubForm(Model model) {
		Club club = new Club();
		model.addAttribute("club", club);
		return "clubs-create";
	}

	@PostMapping("clubs/new")
	public String createClub(@Valid @ModelAttribute("club") ClubDto clubDto, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("club", clubDto);
			return "clubs-create";
		}
		clubService.save(clubDto);
		return "redirect:/clubs";
	}

	@GetMapping("clubs/search")
	public String searchClubs(@RequestParam(value = "query") String query, Model model) {
		List<ClubDto> clubs = clubService.searchClubs(query);
		model.addAttribute("clubs", clubs);
		return "clubs-list";
	}

	@GetMapping("/clubs/{clubId}/delete")
	public String deleteClub(@PathVariable("clubId") Long clubId) {
		clubService.delete(clubId);
		return "redirect:/clubs";
	}

	@GetMapping("/clubs/{clubId}")
	public String clubDetail(@PathVariable("clubId") Long id, Model model) {
		
		UserEntity user = new UserEntity();
		ClubDto clubDto = clubService.findById(id);
		String username = SecurityUtil.getUserSession();
		
		if (username != null) {
			user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		model.addAttribute("user", user);
		model.addAttribute("club", clubDto);
		return "clubs-detail";
	}

	@GetMapping("clubs/{clubId}/edit")
	public String editClubForm(@PathVariable("clubId") Long clubId, Model model) {
		ClubDto club = clubService.findById(clubId);
		model.addAttribute("club", club);
		return "clubs-edit";
	}

	@PostMapping("clubs/{clubId}/edit")
	public String updateClub(@PathVariable("clubId") Long clubId, @Valid @ModelAttribute("club") ClubDto club,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "clubs-edit";
		}
		club.setId(clubId);
		clubService.update(club);
		return "redirect:/clubs";
	}

}
