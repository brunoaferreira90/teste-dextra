package br.com.dextra.builder;

import org.modelmapper.ModelMapper;

import br.com.dextra.dto.StudentDTO;
import br.com.dextra.model.Student;

public class StudentBuilder {
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static Student dtoToEntity(StudentDTO dto) {
		
		return modelMapper.map(dto, Student.class);
		
	}
	
	public static StudentDTO entityToDto(Student dto) {
		
		return modelMapper.map(dto, StudentDTO.class);
		
	}

}
