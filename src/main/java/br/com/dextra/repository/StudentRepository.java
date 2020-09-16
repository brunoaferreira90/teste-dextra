package br.com.dextra.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.dextra.model.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String>{
	
	List<Student> findByHouse(String houseId);

}
