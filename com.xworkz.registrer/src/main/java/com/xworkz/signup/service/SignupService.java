package com.xworkz.signup.service;

import com.xworkz.signup.dto.SignupDTO;

public interface SignupService {

	public boolean validateAndSave(SignupDTO signupDTO);
}
