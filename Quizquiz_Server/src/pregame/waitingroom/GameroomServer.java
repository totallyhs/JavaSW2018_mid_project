package pregame.waitingroom;

import java.net.Socket;

import data.user.User;

public class GameroomServer extends Thread {
	User user;
	Socket socket;
	
	public GameroomServer(Socket socket, User user) {
		this.socket = socket;
		this.user = user;
	}
	
	@Override
	public void run() {
		
	}
}
