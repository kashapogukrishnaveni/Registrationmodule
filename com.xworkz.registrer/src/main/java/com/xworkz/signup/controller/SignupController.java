package com.xworkz.signup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.signup.dto.SignupDTO;
import com.xworkz.signup.service.SignupService;

@Controller
@RequestMapping("/")
public class SignupController {
	@Autowired
	private SignupService signupService;
	
	//PropertyPlaceholderConfigurer
	
	public SignupController() {
		System.out.println("Created \t "+this.getClass().getSimpleName());
	}
	@RequestMapping("Signup.do")
	public String onSignup(SignupDTO signupDTO,ModelMap map) {
		
		try {
			System.out.println("invoked onSignup");
			boolean check = this.signupService.validateAndSave(signupDTO);
			if (check) {
				map.addAttribute("name",signupDTO.getUsername());
				map.addAttribute("Signup", "Data saved");
			} else {
				map.addAttribute("Signup", "Data not saved");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Signup";
	}
}
