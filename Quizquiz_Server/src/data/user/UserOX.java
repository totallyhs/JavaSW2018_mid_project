package data.user;

import java.util.List;

public class UserOX extends User implements UserGamePlay {

	public UserOX(String id, String password) {
		super(id, password);
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
