package com.bennie.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bennie.spring.domain.Person;

public class PersonServiceImpl implements PersonService {

	private static final Logger logger = LoggerFactory
			.getLogger(PersonService.class);

	private final List<Person> persons;

	public PersonServiceImpl() {
		logger.debug("create person list in memory");
		this.persons = new ArrayList<Person>();
		populateData();
	}

	@Override
	public Person get(Integer id) {

		for (Person person : persons) {
			if (person.getId().longValue() == id.longValue())
				return person;
		}
		return null;

	}

	@Override
	public Person add(Person person) {
		Integer newId = 0;
		Boolean hasFoundSuitableId = false;
		while (hasFoundSuitableId == false) {
			for (int i = 0; i < persons.size(); i++) {
				if (persons.get(i).getId().longValue() == newId.longValue()) {
					newId++;
					break;
				}

				if (i == persons.size() - 1) {
					hasFoundSuitableId = true;
				}

			}
		}

		person.setId(newId);
		persons.add(person);
		return person;
	}

	@Override
	public Boolean delete(Integer id) {
		for (Person person : persons) {
			if (person.getId().longValue() == id.longValue()) {
				persons.remove(person);
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean edit(Person person) {
		for (Person p : persons) {
			if (p.getId().longValue() == person.getId().longValue()) {
				persons.remove(p);
				persons.add(person);
				return true;
			}
		}

		return false;

	}

	@Override
	public List<Person> getAll() {
		return this.persons;
	}

	private void populateData() {
		Person person1 = new Person();
		person1.setId(0);
		person1.setFirstName("John");
		person1.setLastName("Smith");
		person1.setMoney(1000.0);

		Person person2 = new Person();
		person2.setId(1);
		person2.setFirstName("Jane");
		person2.setLastName("Adams");
		person2.setMoney(2000.0);

		Person person3 = new Person();
		person3.setId(2);
		person3.setFirstName("Jeff");
		person3.setLastName("Mayer");
		person3.setMoney(3000.0);

		persons.add(person1);
		persons.add(person2);
		persons.add(person3);

	}

}
