package com.bennie.spring.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "persons")
public class PersonList {

	private List<Person> person;

	@XmlElement(required = false)
	public List<Person> getPerson() {
		return person;
	}

	public void setPerson(List<Person> data) {
		this.person = data;
	}

}
