package br.com.dextra.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(HouseNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(HouseNotFoundException e) {

		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setDate(LocalDateTime.now());
		errorDetail.setMessage(e.getMessage());

		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}

}
