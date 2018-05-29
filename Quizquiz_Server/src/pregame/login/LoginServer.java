package pregame.login;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import data.user.User;
import manager.ExistingUser;
import manager.UserConnected;
import manager.WaitingroomHandler;

import java.net.*;
import java.io.*;

/**
 * @author Hee Sang Shin
 * @version 1.0
 * @date 2018.05.29
 * 
 * �α����� ó�����ִ� ����
 *
 */

public class LoginServer extends Thread {
	public User user;
	public Set<User> signedUsers;
	
	// �α��� ���� �� ����
	public static int SUCCESS = 1001;
	public static int FAILED = 1002;
	
	// ��Ʈ��ũ ����
	public Socket socket;
	public ObjectOutputStream oos;
	public Map<String, Object> request;
	
	// ������ - �⺻ ����
	public LoginServer(Socket socket, Map request, User user) {
		try {
			this.socket = socket;
			oos = new ObjectOutputStream(socket.getOutputStream());
			this.request = request;
			this.user = user;
			
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
	
	// ������
	@Override
	public void run() {
		while (socket.isBound()) {
			login(request);
			break;
		}
	}

	// �α��� �޼ҵ�
	/* 
	 * �α��� �� : 
	 * 	1. "login" �޽����� Ŭ���̾�Ʈ���� �����ش�
	 *  2. �α��� ������ int SUCCESS �� �����ش�
	 *     �α��� ���н� int FAILED �� �����ش�
	 *  3. �α����� ������ �и��Ѵ� (Userconnected.connectedUsers pool�� ����)
	 */
	private void login(Map<String, Object> request) {
		User user = (User) request.get("user");
		System.out.println("[LOGIN-SERVER] login wanted user got");
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("mode", "login");
		if (ExistingUser.existingUsers.contains(user)) {
			response.put("result", SUCCESS);
			System.out.println("[LOGIN-SERVER] ok");
			this.user = user;		
			UserConnected uc = new UserConnected(user, socket.getInetAddress());
			UserConnected.connectedUsers.add(uc);
			WaitingroomHandler.usersInWaitingroom.add(user);
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
