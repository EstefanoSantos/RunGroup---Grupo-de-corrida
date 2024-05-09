package com.estefanosantos.club.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.estefanosantos.club.dto.RegistrationDto;
import com.estefanosantos.club.models.UserEntity;
import com.estefanosantos.club.services.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/register")
	public String getRegisterForm(Model model) {
		RegistrationDto user = new RegistrationDto();
		model.addAttribute("user", user);
		return "register";
	}
	
	@PostMapping("/register/save")
	public String createUser(@Valid @ModelAttribute("user") RegistrationDto registrationDto, BindingResult result,  Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("user", registrationDto);
			return "register";
		}
		
		UserEntity userUsername = userService.findByUsername(registrationDto.getUsername());
		
		if (userUsername != null && userUsername.getUsername() != null && !userUsername.getUsername().isEmpty()) {
			return "redirect:/register?fail";
		}
		
		UserEntity userEmail = userService.findByEmail(registrationDto.getEmail());
		
		if (userEmail != null && userEmail.getEmail() != null && !userEmail.getEmail().isEmpty()) {
			return "redirect:/register?fail";
		}
		
		userService.create(registrationDto);
		return "redirect:/clubs?success";
		
	}
}
