package data.user;

import java.util.List;

public interface UserAction {
	public boolean inviteFriend(User user);
	public boolean inviteFriend(List<User> friends);
}
