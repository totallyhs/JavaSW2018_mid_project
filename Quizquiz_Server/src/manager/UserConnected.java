package manager;
import data.user.User;

import java.net.InetAddress;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UserConnected {
	public User user;
	InetAddress ip;
	
	public static Set<UserConnected> connectedUsers;
	
	static {
		connectedUsers = new LinkedHashSet<UserConnected>();
	}
	
	public UserConnected(User user, InetAddress ip) {
		this.user = user;
		this.ip = ip;
		connectedUsers.add(this);
	}
	
}
