package com.xworkz.signup.dao;

import com.xworkz.signup.entity.SignupEntity;

public interface SignupDAO {

	public void save(SignupEntity signupEntity);
	
	public SignupEntity fetchByEmail(String email);
	
	
}
