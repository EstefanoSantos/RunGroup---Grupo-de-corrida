package com.estefanosantos.club.dto;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
	
	private Long id;
	@NotEmpty(message = "Nome de usuário não pode estar vazio.") @NotNull
	@Size(min = 6, max = 25, message = "Nome de usuário deve conter entre {min} e {max} caracteres.")
	private String username;
	@Email(message = "Email deve ser válido.") @NotEmpty(message = "Email não pode estar vazio.") @NotNull
	private String email;
	@NotEmpty(message = "Senha não pode estar vazia.") @NotNull
	@Size(min = 6, max = 15, message = "Senha deve conter entre {min} e {max} dígitos.")
	private String password;
}	
