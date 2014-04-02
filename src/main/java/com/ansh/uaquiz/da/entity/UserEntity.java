package com.ansh.uaquiz.da.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author o_sshyik
 */
@Table(name = "user", schema = "", catalog = "ua_quiz")
@Entity
public class UserEntity {
	private String name;
	private String surname;
	private String login;
	private String password;
	private String email;
	private String role;

	@Column(name = "name", nullable = false, length = 40)
	@Basic
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "surname", nullable = false, length = 40)
	@Basic
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "login", length = 40)
	@Id
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "password", nullable = false, length = 40)
	@Basic
	public String getPassword() {
		return password;
	}

	public void setPassword(String pasword) {
		this.password = pasword;
	}

	@Column(name = "email", nullable = false, unique = true, length = 40)
	@Basic
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "role", nullable = false, length = 10)
	@Basic
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserEntity that = (UserEntity) o;

		if (email != null ? !email.equals(that.email) : that.email != null) return false;
		if (login != null ? !login.equals(that.login) : that.login != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (password != null ? !password.equals(that.password) : that.password != null) return false;
		if (role != null ? !role.equals(that.role) : that.role != null) return false;
		if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (surname != null ? surname.hashCode() : 0);
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		return result;
	}
}
