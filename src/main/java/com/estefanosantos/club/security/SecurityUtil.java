package com.estefanosantos.club.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityUtil {
	
	public static String getUserSession() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			 String currentUsername = authentication.getName();			
			 return currentUsername;
		}
		return null;
	}
}
	