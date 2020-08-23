package br.com.dextra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dextra.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	List<Student> findByHouse(String houseId);

}
