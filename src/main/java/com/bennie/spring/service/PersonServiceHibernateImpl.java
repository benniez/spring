package com.bennie.spring.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bennie.spring.domain.Person;

@Service("personService")
@Transactional
public class PersonServiceHibernateImpl implements PersonService {

	private static final Logger logger = LoggerFactory
			.getLogger(PersonService.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<Person> getAll() {
		logger.debug("Retrieving all persons");
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("From Person");
		return query.list();
	}

	@Override
	public Person get(Integer id) {
		logger.debug("Retrieving person");
		Session session = sessionFactory.getCurrentSession();
		Person person = (Person) session.get(Person.class, id);
		return person;

	}

	@Override
	public Person add(Person person) {
		logger.debug("Add person");
		Session session = sessionFactory.getCurrentSession();
		Integer newId = (Integer) session.save(person);
		return get(newId);
	}

	@Override
	public Boolean delete(Integer id) {
		logger.debug("Deleting existing person");
		Session session = sessionFactory.getCurrentSession();
		Person person = (Person) session.get(Person.class, id);
		session.delete(person);
		return true;

	}

	@Override
	public Boolean edit(Person person) {
		logger.debug("Editing existing person");
		Session session = sessionFactory.getCurrentSession();
		Person existingPerson = (Person) session.get(Person.class,
				person.getId());
		existingPerson.setFirstName(person.getFirstName());
		existingPerson.setLastName(person.getLastName());
		existingPerson.setMoney(person.getMoney());
		session.save(existingPerson);
		return true;
	}
}
