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
public class LoginDAOImpl implements LoginDAO {
	
	@Autowired
	private SessionFactory factory;
	
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public LoginDAOImpl() {
		System.out.println("Created \t "+this.getClass().getSimpleName());
	}


	public SignupEntity fetchByEmailAndPassword(String email, String password) {

		Session session=null;
		
		try {
			session=factory.openSession();
			System.out.println("invoke program...");
			
			Query  query =session.getNamedQuery("fetchByEmailAndPassword");
			
			query.setParameter("email", email);
			query.setParameter("password", password);
			Object result=query.uniqueResult();
			if(Objects.nonNull(result))
			{
				System.out.println("Entity found"+"\t"+email+"\t"+password);
				SignupEntity entity=(SignupEntity) result;
				return entity;
			}
			else
			{
				System.out.println("Entity not found");
				return null;
			}
				
			} catch(HibernateException she) {
				she.printStackTrace();
			} finally {
				if(Objects.nonNull(session));
				System.out.println("close session");
				session.close();
			}

		return null;
	}

}
