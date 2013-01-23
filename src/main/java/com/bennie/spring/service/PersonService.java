package com.bennie.spring.service;

import java.util.List;

import com.bennie.spring.domain.Person;

public interface PersonService {

	Person get(Integer id);

	Person add(Person person);

	Boolean delete(Integer id);

	Boolean edit(Person person);

	List<Person> getAll();

}
