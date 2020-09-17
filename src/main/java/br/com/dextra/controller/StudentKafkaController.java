package br.com.dextra.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dextra.dto.StudentDTO;
import br.com.dextra.service.StudentKafkaService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/student-kafka")
public class StudentKafkaController {
	
	@Autowired
	private StudentKafkaService studentKafkaService;

	@ApiOperation(value = "Create a new Student")
	@PostMapping
	public ResponseEntity<String> create(@Valid @RequestBody StudentDTO dto){
		
		studentKafkaService.sendStudentMessage(dto);
		
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "Update one Student")
	@PutMapping(value = "/{id}")
	public ResponseEntity<String> update(@PathVariable("id") String id, @Valid @RequestBody StudentDTO dto){
		
		studentKafkaService.sendStudentMessage(id, dto);
		
		return ResponseEntity.ok().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors()
		.forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
