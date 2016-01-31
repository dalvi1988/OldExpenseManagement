package com.chaitanya.login.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chaitanya.login.model.Login;

@Repository
public class LoginDAO implements ILoginDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public Login findByUserName(String username) {

		List<Login> loginDetails = new ArrayList<Login>();

		loginDetails = sessionFactory.getCurrentSession().createQuery("from Login where userName=?").setParameter(0, username)
				.list();

		if (loginDetails.size() > 0) {
			return loginDetails.get(0);
		} else {
			return null;
		}

	}

}