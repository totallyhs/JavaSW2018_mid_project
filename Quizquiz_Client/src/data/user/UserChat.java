package data.user;

import java.util.List;

public class UserChat extends User implements UserAction{

	public UserChat(String id, String pass) {
		super(id, pass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean inviteFriend(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean inviteFriend(List<User> friends) {
		// TODO Auto-generated method stub
		return false;
	}

}