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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dextra.dto.StudentDTO;
import br.com.dextra.service.StudentService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService characterService;

	@ApiOperation(value = "Search All Students")
	@GetMapping
	public ResponseEntity<List<StudentDTO>> getAll() {
		return ResponseEntity.ok(characterService.getAll());
	}
	
	@ApiOperation(value = "Search Student by your Id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<StudentDTO> getById(@PathVariable("id") String id) {
		return ResponseEntity.ok(characterService.getById(id));
	}
	
	@ApiOperation(value = "Search Student by your House")
	@GetMapping(value = "/house/{houseId}")
	public ResponseEntity<List<StudentDTO>> getByHouseId(@PathVariable("houseId") String houseId) {
		return ResponseEntity.ok(characterService.getByHouseId(houseId));
	}

	@ApiOperation(value = "Create a new Student")
	@PostMapping
	public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO dto){
		return ResponseEntity.ok(characterService.create(dto));
	}
	
	@ApiOperation(value = "Update one Student")
	@PutMapping(value = "/{id}")
	public ResponseEntity<StudentDTO> update(@PathVariable("id") String id, @Valid @RequestBody StudentDTO dto){
		return ResponseEntity.ok(characterService.update(id, dto));
	}
	
	@ApiOperation(value = "Delete one Student")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
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
