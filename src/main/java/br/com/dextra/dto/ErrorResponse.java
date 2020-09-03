package br.com.dextra.dto;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private String message;
	private String name;
	private String stringValue;
	private String kind;
	private String value;
	private String namepath;

}
