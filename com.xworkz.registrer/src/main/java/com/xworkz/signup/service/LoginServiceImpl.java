package com.xworkz.signup.service;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.signup.dao.LoginDAO;
import com.xworkz.signup.dto.LoginDTO;
import com.xworkz.signup.entity.SignupEntity;
@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDAO loginDAO;

	public LoginServiceImpl() {
		System.out.println("Created \t" + this.getClass().getSimpleName());
	}

	public boolean validateAndSave(LoginDTO loginDTO) {
		boolean valid = false;
		
		try {
			System.out.println("invoked validateAndSave");
			if (Objects.nonNull(loginDTO)) { 
			System.out.println("starting validation for " + loginDTO);
			   String email = loginDTO.getEmail(); 
			   if (email != null && !email.isEmpty() && email.length() >= 10) { 
				   System.out.println("email is valid"); 
				   valid = true; 
				   } else
			   
			  { 
				  System.out.println("email is invalid"); 
				  valid = false;
				} 
			  }
			
			  String password = loginDTO.getPassword();
			  if (valid && password != null &&  !password.isEmpty() && password.length() >= 6) { 
			  System.out.println("password is valid"); 
			  valid = true; 
			  } else {
			  System.out.println("password is invalid"); 
			  valid = false; 
			  }  
			  //if (valid)
			  
			 System.out.println("Data is valid ,will convert  to entity");

			SignupEntity signupEntity = new SignupEntity();
			BeanUtils.copyProperties(loginDTO, signupEntity);

			SignupEntity signupEntity1 = loginDAO.fetchByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());

			if (signupEntity1 != null) {
				System.out.println("Entity is ready \t" + signupEntity1);
				// dao.save(regEntity);
				// System.out.println("Entity is saved");
				loginDAO.fetchByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
				System.out.println("login details exist");
				return true;
			} else {
				System.out.println("login details does not exist");
				return false;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
}
