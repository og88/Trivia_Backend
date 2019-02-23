package com.revature.models;

public class UserMemento {

	private String username;
	private String password;
	private String tempPassword;
	private String email;
	private int experience;
	private int highScore;

	public UserMemento(String username, String password, String tempPassword, String email, int experience, int highScore) {
		super();
		this.username = username;
		this.password = password;
		this.tempPassword = tempPassword;
		this.email = email;
		this.experience = experience;
		this.highScore = highScore;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getTempPassword() {
		return tempPassword;
	}

	public String getEmail() {
		return email;
	}

	public int getExperience() {
		return experience;
	}

	public int getHighScore() {
		return highScore;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", tempPassword=" + tempPassword + ", email="
				+ email + ", experience=" + experience + ", highScore=" + highScore + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + experience;
		result = prime * result + highScore;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((tempPassword == null) ? 0 : tempPassword.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserMemento other = (UserMemento) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (experience != other.experience)
			return false;
		if (highScore != other.highScore)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (tempPassword == null) {
			if (other.tempPassword != null)
				return false;
		} else if (!tempPassword.equals(other.tempPassword))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}
