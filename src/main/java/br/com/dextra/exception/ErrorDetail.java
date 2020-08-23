package br.com.dextra.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorDetail {
	
    private LocalDateTime date;
    private String message;

}
