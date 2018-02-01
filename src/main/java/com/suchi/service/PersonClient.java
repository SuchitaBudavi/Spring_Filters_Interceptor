package com.suchi.service;
import java.util.List;
import org.springframework.http.HttpStatus;

import com.suchi.beans.Person;


public interface PersonClient {
	List<Person> getAllPerson();

	Person getById(Long id);

	HttpStatus addPerson(Person person);

	void updatePerson(Person person);

	void deletePerson(Long id);
}