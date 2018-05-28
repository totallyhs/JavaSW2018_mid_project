package pregame.login;



import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import data.user.User;
import manager.ExistingUser;
import manager.UserConnected;

import java.net.*;
import java.io.*;

public class LoginServer extends Thread {
	public User user;
	public Set<User> signedUsers;
	
	// 로그인 성공 및 실패
	public static int SUCCESS = 1001;
	public static int FAILED = 1002;
	
	// 네트워크 연결
	public Socket socket;
	public ObjectOutputStream oos;
	public Map<String, Object> request;
	
	// 생성자 - 기본 세팅
	public LoginServer(Socket socket, Map request) {
		try {
			this.socket = socket;
			oos = new ObjectOutputStream(socket.getOutputStream());
			this.request = request;
			
			System.out.println("[LOGIN-SERVER] connected");
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	// 쓰레드
	@Override
	public void run() {
		while (socket.isBound()) {
			login(request);
			break;
		}
	}

	// 로그인 메소드
	/* 
	 * 로그인 시 : 
	 * 	1. "login" 메시지를 클라이언트에게 보내준다
	 *  2. 로그인 성공시 int SUCCESS 를 보내준다
	 *     로그인 실패시 int FAILED 를 보내준다
	 *  3. 로그인한 유저로 분리한다 (Userconnected.connectedUsers pool에 저장)
	 */
	private void login(Map<String, Object> request) {
		User user = (User) request.get("user");
		System.out.println("[LOGIN-SERVER] login wanted user got");
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("mode", "login");
		if (ExistingUser.existingUsers.contains(user)) {
			response.put("result", SUCCESS);
			System.out.println("[LOGIN-SERVER] ok");
			UserConnected uc = new UserConnected(user, socket.getInetAddress());
			UserConnected.connectedUsers.add(uc);
		} else {
			response.put("result", FAILED);
		}
		
		try {
			oos.writeObject(response);
			System.out.println("[LOGIN-SERVER] response sent" + response.get("result"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
