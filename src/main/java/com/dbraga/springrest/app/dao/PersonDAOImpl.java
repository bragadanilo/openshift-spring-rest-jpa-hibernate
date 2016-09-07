package com.dbraga.springrest.app.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dbraga.springrest.app.domain.Person;

@Repository("personDAO")
public class PersonDAOImpl extends GenericDAOImpl<Person, Long> implements PersonDAO {

	@Override
	@Transactional
	public List<Person> getAll() {
		Session sess = getSession();
		List<Person> result = sess.createQuery("from Person").list();
		return result;
	}

}
