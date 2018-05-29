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
 * 첫화면에서 로그인 및 회원가입 요청을 듣는 헨들러(서버)
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
			// 로그인 & 회원가입 처리
			while (user == null) {
				request = requestListener(request);
				requestHandler(request);			
			}
			// 로그인 & 회원가입 처리
			
			// 로그인이 되었을 시에 Waitingroom에 입장
			if (user != null) {
				WaitingroomHandler wrs = new WaitingroomHandler(socket, user);
				wrs.start();
			}
		}
	}
	
}
