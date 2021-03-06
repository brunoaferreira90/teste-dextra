package br.com.dextra.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.dextra.builder.StudentBuilder;
import br.com.dextra.dto.HouseResponseDTO;
import br.com.dextra.dto.StudentDTO;
import br.com.dextra.exception.HouseNotFoundException;
import br.com.dextra.exception.StudentNotFoundException;
import br.com.dextra.model.Student;
import br.com.dextra.repository.StudentRepository;

@Service
public class StudentService extends RestService{
	
	private static final String INFORMED_HOUSE_NOT_FOUND = "Informed House not found";

	private static final String INFORMED_STUDENT_NOT_FOUND = "Informed Student not found";

	private static final String HOUSE_ID = "houseId";

	private static final String GET_HOUSE_BY_ID = "/houses/{houseId}";
	
	@Autowired
	private StudentRepository repository;
	
	@Value(value = "${potterapi.token}")
	private String token;
	
	@Value(value = "${potterapi.url}")
	private String url;
	
	public List<StudentDTO> getAll() {
		
		return repository.findAll().stream()
				.filter(result -> !ObjectUtils.isEmpty(result))
				.map(result -> StudentBuilder.entityToDto(result))
				.collect(Collectors.toList());
	}
	
	@CacheEvict(value = {"cacheGetByHouse", "cacheGetById"}, allEntries = true)
	public StudentDTO create(StudentDTO dto){
		
		validateHouse(dto);
		
		Student studentCreated = repository.save(StudentBuilder.dtoToEntity(dto));
		
		return StudentBuilder.entityToDto(studentCreated);
	}
	
	@CacheEvict(value = {"cacheGetByHouse", "cacheGetById"}, allEntries = true)
	public StudentDTO update(Long id, StudentDTO dto){
		
		repository.findById(id).orElseThrow(
				() -> new StudentNotFoundException(INFORMED_STUDENT_NOT_FOUND));
		
		validateHouse(dto);
		
		dto.setId(id);
		
		Student studentUpdated = repository.save(StudentBuilder.dtoToEntity(dto));
		
		return StudentBuilder.entityToDto(studentUpdated);
	}

	private void validateHouse(StudentDTO dto) {
		Map<String, Object> urlParam = new HashMap<>();
		urlParam.put(HOUSE_ID, dto.getHouse());
		
		HouseResponseDTO[] response = (HouseResponseDTO[]) this.get(this.buildUrlWithKey(GET_HOUSE_BY_ID, urlParam), HouseResponseDTO[].class);
		
		if(ObjectUtils.isEmpty(response)) {
			throw new HouseNotFoundException(INFORMED_HOUSE_NOT_FOUND);
		}
	}


	@CacheEvict(value = {"cacheGetByHouse", "cacheGetById"}, allEntries = true)
	public void delete(Long id) {
		Student entity = repository.findById(id).orElseThrow(
				() -> new StudentNotFoundException(INFORMED_STUDENT_NOT_FOUND));
		
		repository.delete(entity);
	}

	@Cacheable(value = "cacheGetByHouse")	
	public List<StudentDTO> getByHouseId(String houseId) {
		return repository.findByHouse(houseId).stream()
				.filter(result -> !ObjectUtils.isEmpty(result))
				.map(result -> StudentBuilder.entityToDto(result))
				.collect(Collectors.toList());
	}

	
	@Cacheable(value = "cacheGetById")
	public StudentDTO getById(Long id) {
		
		Student entity = repository.findById(id).orElseThrow(
				() -> new StudentNotFoundException(INFORMED_STUDENT_NOT_FOUND));
		
		return StudentBuilder.entityToDto(entity);
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
