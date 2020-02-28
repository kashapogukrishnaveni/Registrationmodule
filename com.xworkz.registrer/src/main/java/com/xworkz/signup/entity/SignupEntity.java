package com.xworkz.signup.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "signup_details")
//@NamedQuery({
	//@NamedQuery(name="fetchByEmailAndPassword",query="select se from SinupEntity se where se.email=:email and se.password=:password")
	//@NamedQuery(name="fetchByEmail",query="select se from SignupEntity se where se.email=:email")
	//})
@NamedQuery(name="fetchByEmailAndPassword",
 query="select se from SignupEntity se where se.email=:email and se.password=:password")

public class SignupEntity implements Serializable {
	@Id
	@GenericGenerator(name = "auto", strategy = "increment")
	@GeneratedValue(generator = "auto")
	@Column(name = "id")
	private int id;
	@Column(name = "username")
	private String username;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	

	public SignupEntity() {
		System.out.println("Created \t" + this.getClass().getSimpleName());
	}


	public SignupEntity(int id, String username, String email, String password) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "SignupEntity [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ "]";
	}
}


