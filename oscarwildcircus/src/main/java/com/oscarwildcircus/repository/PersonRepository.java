package com.oscarwildcircus.repository;

import com.oscarwildcircus.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
