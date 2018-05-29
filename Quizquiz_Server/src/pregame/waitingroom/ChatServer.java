package pregame.waitingroom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data.user.User;
import manager.ExistingUser;
import manager.UserConnected;

public class ChatServer extends Thread {
	User user;
	Socket socket;
	
	String targetNick;

	
	ObjectOutputStream oos;
	ObjectInputStream ois;
	public Map<String, Object> request; 
	
	public static List<ChatServer> pools;

	static {
		pools = new ArrayList<>();
	}

	public ChatServer(Socket socket, Map request, User user) {
		
		try {
			this.socket = socket;
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
			this.request = request;
			this.targetNick = user.nickname;
			pools.add(this);
			
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/*@Override
	public void run() {
		while (socket.isBound()) {

			try {
				Map request = (Map) ois.readObject();
				System.out.println("[CHAT-SERVER] " + request + ".." + socket);
				String mode = (String) request.get("mode");
				switch (mode) {
				case "chat":
					chatHandle(request); // 모든 사용자에게 응답을 전송
					break;
				// case "exit" :
				// exitHandle(request); // 모든 사용자에게 응답을 전송
				// break;
				// case "change" :
				// changeHandle(request); // 요청보낸 사용자에게 응답을 전송
				// break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("[CHAT-SERVER] " + socket + " disconnected");
				ids.remove(targetNick);
				pools.remove(this);
				break;
			}
		}
	}*/
	
	// 쓰레드
		@Override
		public void run() {
			while (socket.isBound()) {
				chat(request);
				break;
			}
		}
		
		public void chat(Map<String, Object> request)  {
			//User user = (User) request.get("nickname");
			Map<String, Object> response = new HashMap<String, Object>();
			
			
			String[] ban = "바보,멍청이,욕".split(",");
			String message = (String) request.get("message");
			for (String m : ban) {
				message = message.replace(m, "****");
			}
			request.put("message", message);
			request.put("talker", targetNick);

			for (ChatServer chat : pools) {
				try {
					chat.oos.writeObject(request);
				} catch (IOException e) {
				
					e.printStackTrace();
				}
			}	
		}
}