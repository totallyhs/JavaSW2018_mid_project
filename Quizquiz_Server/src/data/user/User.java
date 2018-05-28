package data.user;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
	public String id;
	public String password;
	
	public String nickname;
	
	public int score;
	
	public List<User> friends;
	
	public User() {
		
	}
	
	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		User otherUser = (User) obj;
		
		if (this.id.equals(otherUser.id) && this.password.equals(otherUser.password)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	
	
}
