package manager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.gameroom.Gameroom;
import data.user.User;
import pregame.waitingroom.ChatServer;
import pregame.waitingroom.GameroomRefreshServer;
import pregame.waitingroom.GameroomServer;


/**
 * @author Hee Sang Shin
 * @version 1.0
 * @date 2018.05.29
 * 
 * ���濡�� Ŭ���̾�Ʈ ��û�� ��� ��鷯(����)
 *
 */

public class WaitingroomHandler extends Thread {
	User user;
	Socket socket;
	
	public ObjectInputStream ois;
	
	// ���濡 �ִ� ���� ����Ʈ
	public static List<User> usersInWaitingroom;
	
	static {
		usersInWaitingroom = new ArrayList<User>();
	}
	
	// ������
	public WaitingroomHandler(Socket socket, User user) {
		try {
			this.socket = socket;
			ois = new ObjectInputStream(socket.getInputStream());
			this.user = user;
			usersInWaitingroom.add(user);
			System.out.println(socket.getInetAddress() + " connected");			
		} catch (IOException e) {
			System.out.println(socket.getInetAddress() + " disconnected");
			e.printStackTrace();
		}
	}
	
	
	// Ŭ���̾�Ʈ�κ��� ���� ��� �޴� �޼ҵ�
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
	
	// ���濡�� ä��, ���ӹ�����, ���ӹ� refresh, ���ӹ� ����� ������� ������.
	@Override
	public void run() {
		if (usersInWaitingroom.contains(user)) {
			Map request = null;
			while (socket.isBound() && usersInWaitingroom.contains(user)) {
				request = requestListener(request);
				String mode = (String)request.get("mode");
				System.out.println("[WAITINGROOM-SERVER] mode " + mode);
				switch(mode) {
				case "chat":
					ChatServer cs = new ChatServer(socket, request, user);
					cs.start();
					break;
				case "joinGameroom":
					GameroomServer gs = new GameroomServer(socket, request, user);
					gs.start();
					break;
				case "refreshGameroomList":
					GameroomRefreshServer grs = new GameroomRefreshServer(socket, request, user);
					grs.start();
					break;
				case "createGameroom":
					break;
				}
			}
			
			
		}
	}
	
}
