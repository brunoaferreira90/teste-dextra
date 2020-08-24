package br.com.dextra.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dextra.builder.StudentServiceMockBuilder;
import br.com.dextra.dto.HouseResponseDTO;
import br.com.dextra.exception.HouseNotFoundException;
import br.com.dextra.repository.StudentRepository;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
	
	private static final String HTTP_TESTE_COM = "http://teste.com";

	@Mock
	private StudentRepository repository;
	
	@InjectMocks
	private StudentService service;
	
	@Mock
	private RestTemplate restTemplate;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Before
	public void setMocks() {
		ReflectionTestUtils.setField(service, "url", HTTP_TESTE_COM);
		ReflectionTestUtils.setField(service, "token", "token");
	}
	
	
	@Test
	public void saveStudentTest() throws JsonProcessingException {
		
		Mockito.when(repository.save(Mockito.any())).thenReturn(StudentServiceMockBuilder.buildStudent());
		
		HouseResponseDTO[] houseAsList = {StudentServiceMockBuilder.buildHouseDTO(), StudentServiceMockBuilder.buildHouseDTO()};
		
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(mapper.writeValueAsString(houseAsList), HttpStatus.OK);
		
		Mockito.when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
             .thenReturn(responseEntity);
		
		service.create(StudentServiceMockBuilder.buildStudentDTO());
		
		assertEquals(StudentServiceMockBuilder.buildStudentDTO().getId(), StudentServiceMockBuilder.buildStudent().getId());
		
	}
	
	@Test(expected = HouseNotFoundException.class)
	public void saveStudentHouseNotFoundTest() throws JsonProcessingException {
		
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(mapper.writeValueAsString(new HouseResponseDTO()), HttpStatus.OK);
		
		Mockito.when(restTemplate.exchange(
				ArgumentMatchers.anyString(),
				ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(),
				ArgumentMatchers.<Class<String>>any()))
		.thenReturn(responseEntity);
		
		service.create(StudentServiceMockBuilder.buildStudentDTO());
		
	}


}
