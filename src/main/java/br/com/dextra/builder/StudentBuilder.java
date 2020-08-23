package br.com.dextra.builder;

import org.modelmapper.ModelMapper;

import br.com.dextra.dto.StudentDTO;
import br.com.dextra.model.Student;

public class StudentBuilder {
	
	public static Student dtoToEntity(StudentDTO dto) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(dto, Student.class);
		
	}

}
