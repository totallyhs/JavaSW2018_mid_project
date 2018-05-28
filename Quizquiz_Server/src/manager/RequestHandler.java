package manager;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import data.user.User;
import pregame.login.LoginServer;
import pregame.login.SignUpServer;

public class RequestHandler extends Thread {
	public static List<RequestHandler> users;
	
	Socket socket;
	public ObjectInputStream ois;
	
	static {
		users = new LinkedList<RequestHandler>();
	}
	
	public RequestHandler(Socket socket) {
		try {
			this.socket = socket;
			ois = new ObjectInputStream(socket.getInputStream());
			users.add(this);
			
			System.out.println(socket.getInetAddress() + " connected");
		} catch (IOException e) {
			System.out.println(socket.getInetAddress() + " disconnected");
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		Map request = null;
		while (socket.isBound()) {
			try {
				request = (Map)ois.readObject();
				System.out.println("[REQUEST HANDLER] request received");
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			String mode = (String) request.get("mode");
			System.out.println("[REQUEST HANDLER] request mode : " + mode);
			
			switch(mode) {
			case "login":
				LoginServer ls = new LoginServer(socket, request);
				ls.start();
				break;
			case "join":
				SignUpServer ss;
				ss = new SignUpServer(socket, request);
				ss.start();
				break;
			}			
		}
	}
	
}
