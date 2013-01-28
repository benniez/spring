package com.bennie.spring.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DBUSER")
public class DbUser implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;
	@Column(name = "USER_NAME")
	private String username;
	@Column(name = "PASSWORD")
	private String password;
	// Access level of the user. 1 = Admin user 2 = Regular user
	@Column(name = "ACCESS")
	private Integer access;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAccess() {
		return access;
	}

	public void setAccess(Integer access) {
		this.access = access;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
