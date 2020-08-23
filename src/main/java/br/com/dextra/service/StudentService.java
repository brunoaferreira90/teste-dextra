package br.com.dextra.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.dextra.builder.StudentBuilder;
import br.com.dextra.dto.HouseResponseDTO;
import br.com.dextra.dto.StudentDTO;
import br.com.dextra.exception.HouseNotFoundException;
import br.com.dextra.exception.StudentNotFoundException;
import br.com.dextra.model.Student;
import br.com.dextra.repository.StudentRepository;

@Service
public class StudentService extends RestService{
	
	private static final String HOUSE_ID = "houseId";

	private static final String GET_HOUSE_BY_ID = "/houses/{houseId}";

	@Autowired
	private StudentRepository repository;
	
	@Value(value = "${potterapi.token}")
	private String token;
	
	@Value(value = "${potterapi.url}")
	private String url;
	
	public List<Student> getAll() {
		return repository.findAll();
	}
	
	@HystrixCommand
	public Student create(StudentDTO dto){
		
		validateHouse(dto);
		
		return repository.save(StudentBuilder.dtoToEntity(dto));
	}
	
	@HystrixCommand
	public Student update(Long id, StudentDTO dto){
		
		repository.findById(id).orElseThrow(
				() -> new StudentNotFoundException("Informed Student dont Exists"));
		
		validateHouse(dto);
		
		dto.setId(id);
		
		return repository.save(StudentBuilder.dtoToEntity(dto));
	}

	private void validateHouse(StudentDTO dto) {
		Map<String, Object> urlParam = new HashMap<>();
		urlParam.put(HOUSE_ID, dto.getHouse());
		
		HouseResponseDTO[] response = (HouseResponseDTO[]) getListHouseResponseDTO(urlParam);
		
		if(ObjectUtils.isEmpty(response)) {
			throw new HouseNotFoundException("Informed House Dont Exists");
		}
	}

	@Cacheable(value = "cacheHouseResponseDTO")
	private Object getListHouseResponseDTO(Map<String, Object> urlParam) {
		return get(buildUrlWithKey(GET_HOUSE_BY_ID, urlParam), HouseResponseDTO[].class);
	}


	public void delete(Long id) {
		Student entity = repository.findById(id).orElseThrow(
				() -> new StudentNotFoundException("Informed Student dont Exists"));
		
		repository.delete(entity);
	}

	public List<Student> getByHouseId(String houseId) {
		return repository.findByHouse(houseId);
	}

	public Student getById(Long id) {
		
		Student entity = repository.findById(id).orElseThrow(
				() -> new StudentNotFoundException("Informed Student dont Exists"));
		
		return entity;
	}
	
	
	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public String getToken() {
		return token;
	}
	
}
