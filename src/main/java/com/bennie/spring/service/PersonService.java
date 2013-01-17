package com.bennie.spring.service;

import java.util.List;

import com.bennie.spring.domain.Person;

public interface PersonService {

	Person get(Long id);

	Person add(Person person);

	Boolean delete(Long id);

	Boolean edit(Person person);

	List<Person> getAll();

}
