package br.com.dextra.builder;

import org.apache.commons.lang.RandomStringUtils;

import br.com.dextra.dto.HouseResponseDTO;
import br.com.dextra.dto.StudentDTO;
import br.com.dextra.model.Student;

public class StudentServiceMockBuilder {

	public static Student buildStudent() {
		
		Student student = new Student();
		student.setHouse("houseId");
		student.setName(RandomStringUtils.random(1));
		student.setRole(RandomStringUtils.random(1));
		student.setSchool(RandomStringUtils.random(1));
		student.setId(RandomStringUtils.random(1));
		
		return student;
		
	}

	public static StudentDTO buildStudentDTO() {
		
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setHouse("houseId");
		studentDTO.setName(RandomStringUtils.random(1));
		studentDTO.setRole(RandomStringUtils.random(1));
		studentDTO.setSchool(RandomStringUtils.random(1));
		studentDTO.setId(RandomStringUtils.random(1));
		
		return studentDTO;
		
	}
	
	public static HouseResponseDTO buildHouseDTO() {
		HouseResponseDTO houseResponse = new HouseResponseDTO();
		houseResponse.setId("houseId");
		houseResponse.setName("name");
		houseResponse.setSchool("school");
		return houseResponse;
	}
	
}
