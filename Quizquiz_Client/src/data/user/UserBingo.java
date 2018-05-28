package data.user;

import java.util.List;

public class UserBingo extends User implements UserGamePlay {

	public UserBingo(String id, String pass) {
		super(id, pass);
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