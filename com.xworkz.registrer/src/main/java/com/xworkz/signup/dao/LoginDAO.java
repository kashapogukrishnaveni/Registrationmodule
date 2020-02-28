package com.xworkz.signup.dao;


import com.xworkz.signup.entity.SignupEntity;

public interface LoginDAO {
 
	public SignupEntity fetchByEmailAndPassword(String email,String password);
	
}
