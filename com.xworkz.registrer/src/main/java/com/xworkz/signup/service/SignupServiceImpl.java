package com.xworkz.signup.service;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.signup.dao.SignupDAO;
import com.xworkz.signup.dto.SignupDTO;
import com.xworkz.signup.entity.SignupEntity;

@Service
public class SignupServiceImpl implements SignupService {
	@Autowired
	private SignupDAO signupDAO;

	public SignupServiceImpl() {
		System.out.println("Created \t " + this.getClass().getSimpleName());
	}

	public boolean validateAndSave(SignupDTO signupDTO) {
		boolean valid = false;
		try {
			System.out.println("save invoked " + signupDTO);

			if (Objects.nonNull(signupDTO)) {
				System.out.println("starting validation for " + signupDTO);

				String username = signupDTO.getUsername();
				if (username != null && !username.isEmpty() && username.length() >= 5) {
					System.out.println("Username is valid");
					valid = true;
				} else {
					System.out.println("username is invalid");
					if (username == null) {
						System.out.println("username is null");
					}
					if (username != null && username.length() < 5) {
						System.out.println("name length is less than 5");
					}
					valid = false;
				}

				String email = signupDTO.getEmail();
				if (valid && email != null && !email.isEmpty()) {
					System.out.println("email is valid");
					valid = true;
				} else {
					if (valid) {
						System.out.println("email is invalid");
						valid = false;

					}
				}

				String password = signupDTO.getPassword();
				if (valid && password != null && !password.isEmpty() && password.length() >= 6) {
					System.out.println("password is valid");
					valid = true;
				} else {
					if (valid) {
						System.out.println("password is invalid");
						valid = false;

					}
				}

			}

			// return valid;

			if (valid) {
				System.out.println("Data is valid ,will convert  to entity");

				SignupEntity signupEntity = new SignupEntity();

				BeanUtils.copyProperties(signupDTO, signupEntity);

				SignupEntity fromdb = this.signupDAO.fetchByEmail(signupDTO.getEmail());
				if (fromdb == null) {
					System.out.println("entity is ready \t " + signupEntity);

					System.out.println("email does not exist in the db");

					signupDAO.save(signupEntity);
				} else {
					System.out.println("email already exists, cannot save into db");
					valid=false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
