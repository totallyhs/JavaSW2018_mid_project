package data.user;

import java.util.List;

public class User extends Identification {
	public String nickname;
	
	public int score;
	
	public List<User> friends;
	
	public User() {
		
	}
	
	public User(String id, String password) {
		super(id, password);
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
		return this.id.hashCode();
	}
	
	
}


