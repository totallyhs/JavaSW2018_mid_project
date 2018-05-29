package pregame.waitingroom;

import java.net.Socket;

import data.user.User;

public class ChatServer extends Thread {
	User user;
	Socket socket;
	
	public ChatServer(Socket socket, User user) {
		this.socket = socket;
		this.user = user;
	}
	
	@Override
	public void run() {

	}
}
