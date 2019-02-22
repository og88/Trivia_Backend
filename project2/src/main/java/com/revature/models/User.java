package com.revature.models;

public class User {
	private String username;
	private String password;
	private String tempUserName;
	private String email;
	private int experience;
	private int highScore;
	
	public User() {
		super();
	}

	public User(String username, String password, String tempUserName, String email, int experience, int highScore) {
		super();
		this.username = username;
		this.password = password;
		this.tempUserName = tempUserName;
		this.email = email;
		this.experience = experience;
		this.highScore = highScore;
	}
	
	public User(String username, String password, int experience, int highScore) {
		this.username = username;
		this.password = password;
		this.experience = experience;
		this.highScore = highScore;
	}

	public User(String username, int experience, int highScore, String email) {
		this.username = username;
		this.experience = experience;
		this.highScore = highScore;
		this.email = email;
	}
	
	public User(String username, int highScore) {
		this.username = username;
		this.highScore = highScore;
	}

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

	public String getTempUsername() {
		return tempUserName;
	}

	public void setTempUsername(String tempUserName) {
		this.tempUserName = tempUserName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", tempUserName=" + tempUserName + ", email="
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
		result = prime * result + ((tempUserName == null) ? 0 : tempUserName.hashCode());
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
		User other = (User) obj;
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
		if (tempUserName == null) {
			if (other.tempUserName != null)
				return false;
		} else if (!tempUserName.equals(other.tempUserName))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	public UserMemento CreateMemento() {
		
		return new UserMemento(this.username, this.password, this.tempUserName, this.email, this.experience, this.highScore);
		
	}
	
	public void SetMemento(UserMemento memento) {
		
		this.username = memento.getUsername();
		this.password = memento.getPassword();
		this.tempUserName = memento.gettempUserName();
		this.email = memento.getEmail();
		this.experience = memento.getExperience();
		this.highScore = memento.getHighScore();
		
	}
}
