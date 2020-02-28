package com.xworkz.signup.dao;

import java.util.Objects;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.signup.entity.SignupEntity;

@Repository
public class SignupDAOImpl implements SignupDAO {
	@Autowired
	private SessionFactory factory;

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public SignupDAOImpl() {
		System.out.println("Created \t" + this.getClass().getSimpleName());
	}

	public void save(SignupEntity signupEntity) {
		System.out.println("Invoking save method");
		Session session = null;
		try {

			System.out.println("session created");
			session = factory.openSession();
			System.out.println("Transaction begun");
			session.beginTransaction();
			System.out.println("Entity saving...");
			session.save(signupEntity);
			System.out.println("Commiting....");
			session.getTransaction().commit();
			System.out.println("Data saved");
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("INFO:" + e.getMessage());
		}

		// return 0;

	}

	public SignupEntity fetchByEmail(String email) {
		Session session = factory.openSession(); // implement is created,connection is association
		// session CRUD operations,tx,query,criteria
		try {
			String syntax = "select se from SignupEntity se where se.email = '" + email + "'";
			Query query = session.createQuery(syntax);
			Object result = query.uniqueResult();
			if (Objects.nonNull(result)) {
				System.out.println("Entity is Found \t " + email);

				 return (SignupEntity) result;
			} else {
				System.out.println("Entity is not Found \t " + email);
				// return null;
			}
			SignupEntity entity = (SignupEntity) result;
			return entity;
		} catch (HibernateException she) {
			she.printStackTrace();
		} finally {
			if (Objects.nonNull(session)) {
				System.out.println("close session");
				session.close();
			}
		}
		return null;
	}

}
