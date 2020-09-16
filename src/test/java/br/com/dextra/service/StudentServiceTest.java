//package br.com.dextra.service;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.util.ReflectionTestUtils;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import br.com.dextra.builder.StudentServiceMockBuilder;
//import br.com.dextra.dto.HouseResponseDTO;
//import br.com.dextra.dto.StudentDTO;
//import br.com.dextra.exception.HouseNotFoundException;
//import br.com.dextra.exception.StudentNotFoundException;
//import br.com.dextra.model.Student;
//import br.com.dextra.repository.StudentRepository;
//
//@RunWith(MockitoJUnitRunner.Silent.class)
//public class StudentServiceTest {
//	
//	private static final String HTTP_TESTE_COM = "http://teste.com";
//
//	@Mock
//	private StudentRepository repository;
//	
//	@InjectMocks
//	private StudentService service;
//	
//	@Mock
//	private RestTemplate restTemplate;
//	
//	private ObjectMapper mapper = new ObjectMapper();
//	
//	@Before
//	public void setMocks() {
//		ReflectionTestUtils.setField(service, "url", HTTP_TESTE_COM);
//		ReflectionTestUtils.setField(service, "token", "token");
//	}
//	
//	
//	@Test
//	public void saveStudentTest() throws JsonProcessingException {
//		
//		Mockito.when(repository.save(Mockito.any())).thenReturn(StudentServiceMockBuilder.buildStudent());
//		
//		HouseResponseDTO[] houseAsList = {StudentServiceMockBuilder.buildHouseDTO(), StudentServiceMockBuilder.buildHouseDTO()};
//		
//		ResponseEntity<String> responseEntity = new ResponseEntity<String>(mapper.writeValueAsString(houseAsList), HttpStatus.OK);
//		
//		Mockito.when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<String>>any()))
//             .thenReturn(responseEntity);
//		
//		service.create(StudentServiceMockBuilder.buildStudentDTO());
//		
//		assertEquals(StudentServiceMockBuilder.buildStudentDTO().getId(), StudentServiceMockBuilder.buildStudent().getId());
//		
//	}
//	
//	@Test(expected = HouseNotFoundException.class)
//	public void saveStudentHouseNotFoundTest() throws JsonProcessingException {
//		
//		ResponseEntity<String> responseEntity = new ResponseEntity<String>(mapper.writeValueAsString(new HouseResponseDTO()), HttpStatus.OK);
//		
//		Mockito.when(restTemplate.exchange(
//				ArgumentMatchers.anyString(),
//				ArgumentMatchers.any(HttpMethod.class),
//				ArgumentMatchers.any(),
//				ArgumentMatchers.<Class<String>>any()))
//		.thenReturn(responseEntity);
//		
//		service.create(StudentServiceMockBuilder.buildStudentDTO());
//		
//	}
//	
//	@Test
//	public void updateStudentTest() throws JsonProcessingException {
//		
//		Mockito.when(repository.save(Mockito.any())).thenReturn(StudentServiceMockBuilder.buildStudent());
//		
//		Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(StudentServiceMockBuilder.buildStudent()));
//		
//		HouseResponseDTO[] houseAsList = {StudentServiceMockBuilder.buildHouseDTO(), StudentServiceMockBuilder.buildHouseDTO()};
//		
//		ResponseEntity<String> responseEntity = new ResponseEntity<String>(mapper.writeValueAsString(houseAsList), HttpStatus.OK);
//		
//		Mockito.when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<String>>any()))
//             .thenReturn(responseEntity);
//		
//		service.update(Mockito.anyLong(), StudentServiceMockBuilder.buildStudentDTO());
//		
//		assertEquals(StudentServiceMockBuilder.buildStudentDTO().getId(), StudentServiceMockBuilder.buildStudent().getId());
//		
//	}
//	
//	@Test(expected = HouseNotFoundException.class)
//	public void updateStudentHouseNotFoundTest() throws JsonProcessingException {
//		
//		Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(StudentServiceMockBuilder.buildStudent()));
//		
//		ResponseEntity<String> responseEntity = new ResponseEntity<String>(mapper.writeValueAsString(new HouseResponseDTO()), HttpStatus.OK);
//		
//		Mockito.when(restTemplate.exchange(
//				ArgumentMatchers.anyString(),
//				ArgumentMatchers.any(HttpMethod.class),
//				ArgumentMatchers.any(),
//				ArgumentMatchers.<Class<String>>any()))
//		.thenReturn(responseEntity);
//		
//		service.update(Mockito.anyLong(), StudentServiceMockBuilder.buildStudentDTO());
//		
//	}
//
//	@Test(expected = StudentNotFoundException.class)
//	public void updateStudentNotFoundTest() throws JsonProcessingException {
//		
//		service.update(Mockito.anyLong(), StudentServiceMockBuilder.buildStudentDTO());
//		
//	}
//	
//	@Test
//	public void getAllStudentsTest() {
//		
//		List<Student> asList = Arrays.asList(StudentServiceMockBuilder.buildStudent(), StudentServiceMockBuilder.buildStudent());
//		
//		Mockito.when(repository.findAll()).thenReturn(asList);
//		
//		List<StudentDTO> all = service.getAll();
//		
//		assertNotNull(all);
//		
//	}
//	
//	@Test
//	public void deleteStudentTest() {
//		
//		Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(StudentServiceMockBuilder.buildStudent()));
//		
//		Mockito.doNothing().when(repository).delete(StudentServiceMockBuilder.buildStudent());
//		
//		service.delete(Mockito.anyLong());
//		
//	}
//	
//	@Test(expected = StudentNotFoundException.class)
//	public void deleteStudentNotFoundTest() {
//		
//		service.delete(Mockito.anyLong());
//		
//	}
//	
//	@Test
//	public void getStudentsByHouseTest() {
//		
//		List<Student> asList = Arrays.asList(StudentServiceMockBuilder.buildStudent(), StudentServiceMockBuilder.buildStudent());
//		
//		Mockito.when(repository.findByHouse(Mockito.anyString())).thenReturn(asList);
//		
//		List<StudentDTO> all = service.getByHouseId(Mockito.anyString());
//		
//		assertNotNull(all);
//		
//	}
//	
//	@Test
//	public void getStudentByIdTest() {
//		
//		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(StudentServiceMockBuilder.buildStudent()));
//		
//		StudentDTO student = service.getById(Mockito.anyLong());
//		
//		assertNotNull(student);
//		
//	}
//	
//	@Test(expected = StudentNotFoundException.class)
//	public void getStudentByIdNotFoundTest() {
//		
//		service.getById(Mockito.anyLong());
//		
//	}
//
//}
