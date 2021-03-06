package pregame.waitingroom;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import data.gameroom.Gameroom;
import data.user.User;
import manager.WaitingroomHandler;

/**
 * @author Hee Sang Shin
 * @version 1.0
 * @date 2018.05.29
 * 
 * 대기방에서 게임방 리스트를 새로고침 요청을 처리해주는 서버
 *
 */

public class GameroomRefreshServer extends Thread {
	User user;
	Map request;
	
	public static List<Gameroom> gameroomsCreated;
	
	// 로그인 성공 및 실패
	public static int SUCCESS = 2011;
	public static int FAILED = 2012;
		
	// 네트워크 연결
	public Socket socket;
	public ObjectOutputStream oos;
	
	static {
		gameroomsCreated = new LinkedList<Gameroom>();
	}
	
	public GameroomRefreshServer(Socket socket, Map request, User user) {
		try {
			this.socket = socket;
			oos = new ObjectOutputStream(socket.getOutputStream());
			this.user = user;
			this.request = request;
			System.out.println("[GAMEROOM-SERVER] connected");
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	

	@Override
	public void run() {
		while (socket.isBound()) {
			gameroomJoin(request);
			break;
		}
	}
	
	private void gameroomJoin(Map<String, Object> request) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("mode", "gameroomJoin");
		
		int gameroom = (int)request.get("gameroom");
		
		if (gameroomsCreated.contains(gameroom) && !gameroomsCreated.get(gameroom).isFull) {
			gameroomsCreated.get(gameroom).joinedUsers.add(user);
			WaitingroomHandler.usersInWaitingroom.remove(user);
			if (gameroomsCreated.get(gameroom).joinedUsers.size() == gameroomsCreated.get(gameroom).maxPlayers) {
				gameroomsCreated.get(gameroom).isFull = true;
			}
			response.put("result", SUCCESS);
			System.out.println("[GAMEROOM-SERVER] ok");
		} else {
			response.put("result", FAILED);
		}
			response.put("gameroom", gameroom);
			response.put("gameroomList", gameroomsCreated);
			
		try {
			oos.writeObject(response);
			System.out.println("[GAMEROOMJOIN-SERVER] response sent" + response.get("result"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void gameroomListRefresh(Map<String, Object> request) {
		
	}
	
	
}
