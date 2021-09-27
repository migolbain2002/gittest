package test.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import test.project.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	
	@Query("SELECT p FROM Person p WHERE p.identification = :identification")
    Person getByIdentification(@Param("identification") Long identification);
	
}

