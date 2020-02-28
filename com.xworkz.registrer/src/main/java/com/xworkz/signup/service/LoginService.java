package com.xworkz.signup.service;

import com.xworkz.signup.dto.LoginDTO;


public interface LoginService {

	public boolean validateAndSave(LoginDTO loginDTO);
	
}
