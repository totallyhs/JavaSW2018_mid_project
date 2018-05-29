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
 * 대기방에서 클라이언트 요청을 듣는 헨들러(서버)
 *
 */

public class WaitingroomHandler extends Thread {
	User user;
	Socket socket;
	
	public ObjectInputStream ois;
	
	// 대기방에 있는 유저 리스트
	public static List<User> usersInWaitingroom;
	
	static {
		usersInWaitingroom = new ArrayList<User>();
	}
	
	// 생성자
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
	
	
	// 클라이언트로부터 오는 통신 받는 메소드
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
	
	// 대기방에서 채팅, 게임방접속, 게임방 refresh, 게임방 만들기 쓰레드로 보내줌.
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
