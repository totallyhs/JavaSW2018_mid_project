package manager;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import data.user.User;
import pregame.login.LoginServer;
import pregame.login.SignUpServer;

/**
 * @author Hee Sang Shin
 * @version 1.0
 * @date 2018.05.29
 * 
 * ùȭ�鿡�� �α��� �� ȸ������ ��û�� ��� ��鷯(����)
 *
 */

public class LoginRequestHandler extends Thread {
	public static List<LoginRequestHandler> users;
	
	User user;
	
	Socket socket;
	public ObjectInputStream ois;
	
	static {
		users = new LinkedList<LoginRequestHandler>();
	}
	
	public LoginRequestHandler(Socket socket) {
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
	
	private Map requestListener(Map request) {
		try {
			request = (Map)ois.readObject();
			System.out.println("[LOGIN REQUEST HANDLER] request received");
			return request;
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return request;
		}
	}
	
	private void requestHandler(Map request) {
		String mode = (String) request.get("mode");
		System.out.println("[LOGIN REQUEST HANDLER] request mode : " + mode);				
		switch(mode) {
		case "login":
			LoginServer ls = new LoginServer(socket, request, user);
			ls.start();
			break;
		case "join":
			SignUpServer ss;
			ss = new SignUpServer(socket, request);
			ss.start();
			break;
		}	
	}
	
	
	
	@Override
	public void run(){
		Map request = null;
		while (socket.isBound()) {
			// �α��� & ȸ������ ó��
			while (user == null) {
				request = requestListener(request);
				requestHandler(request);			
			}
			// �α��� & ȸ������ ó��
			
			// �α����� �Ǿ��� �ÿ� Waitingroom�� ����
			if (user != null) {
				WaitingroomHandler wrs = new WaitingroomHandler(socket, user);
				wrs.start();
			}
		}
	}
	
}
