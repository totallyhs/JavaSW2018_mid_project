package pregame.waitingroom;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Game.BingoGameServer;
import Game.CardGameServer;
import data.gameroom.Gameroom;
import data.user.User;

public class CreatroomServer extends Thread{

	public User user;
	public Set<User> signedUsers;
	
	public static int SUCCESS = 1031;
	public static int FAILED = 1032;

	Gameroom gameroom;
	// 네트워크 연결
	public Socket socket;
	public ObjectOutputStream oos;
	public Map<String, Object> request;
	
	
	public CreatroomServer(Socket socket, User user, Map request) {							//생성자
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

	
	
	public void run() {
		while(socket.isBound()) {
			createRoom(request);
		}
		
	}
	
	public void createRoom(Map<String, Object> request) {
			User user = (User) request.get("user");
			this.user = user;		
			Gameroom gameroom;
			Map<String, Object> response = new HashMap<String, Object>();
			String game = (String)request.get("game");
			if()
			switch(game) {
			case "bingo" :{
				signedUsers.add(user);
				BingoGameServer bg = new BingoGameServer();
				bg.start();
				response.put("result", SUCCESS);
			try {
				oos.writeObject(response);
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
			case "card" :{
				signedUsers.add(user);
				CardGameServer cg = new CardGameServer();
				cg.start();
				response.put("result", SUCCESS);
			try {
				oos.writeObject(response);
			} catch (IOException e) {
				e.printStackTrace();
			}
			response.put("result", FAILED);
			}
			}
		}
}
