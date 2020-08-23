package br.com.dextra.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dextra.dto.StudentDTO;
import br.com.dextra.model.Student;
import br.com.dextra.service.StudentService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService characterService;

	@GetMapping
	public ResponseEntity<List<Student>> getAll() {
		return ResponseEntity.ok(characterService.getAll());
	}
	
	@GetMapping(params = "/{id}")
	public ResponseEntity<Student> getById(@RequestParam Long id) {
		return ResponseEntity.ok(characterService.getById(id));
	}
	
	@GetMapping(params = "/house/{houseId}")
	public ResponseEntity<List<Student>> getByHouseId(@RequestParam String houseId) {
		return ResponseEntity.ok(characterService.getByHouseId(houseId));
	}

	@ApiOperation(value = "Create a new Student")
	@PostMapping
	public ResponseEntity<Student> create(@Valid @RequestBody StudentDTO dto){
		return ResponseEntity.ok(characterService.create(dto));
	}
	
	@ApiOperation(value = "Update one Student")
	@PutMapping(params = "/{id}")
	public ResponseEntity<Student> update(@RequestParam Long id, @Valid @RequestBody StudentDTO dto){
		return ResponseEntity.ok(characterService.update(id, dto));
	}
	
	@ApiOperation(value = "Delete one Student")
	@DeleteMapping(params = "/{id}")
	public ResponseEntity<?> delete(@RequestParam Long id){
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
