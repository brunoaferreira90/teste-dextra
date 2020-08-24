package br.com.dextra;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import br.com.dextra.repository.StudentRepository;
import br.com.dextra.service.StudentService;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
	
	@Mock
	private StudentRepository repository;
	
	@InjectMocks
	private StudentService service;
	
	@Mock
	private RestTemplate restTemplate;
	
	@Mock
	private String token;
	
	@Mock
	private String url;
	
	@Test
	public void createTest() {
		
	}

}
