package br.com.dextra.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class StudentDTO {
	
	private Long id;
	
	@NotBlank(message = "Campo nome não pode ser vazio")
	private String name;
	
	@NotBlank(message = "Campo função não pode ser vazio")
	private String role;
	
	@NotBlank(message = "Campo escola não pode ser vazio")
	private String school;
	
	@NotBlank(message = "Campo casa não pode ser vazio")
	private String house;
	
	@NotBlank(message = "Campo patrono não pode ser vazio")
	private String patronus;

}
