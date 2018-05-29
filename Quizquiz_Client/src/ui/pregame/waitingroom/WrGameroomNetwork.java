package pregame.waitingroom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import data.gameroom.Gameroom;
import data.network.ClientNetwork;

public class WrGameroomNetwork {
	ClientNetwork clientNetwork;
	
	public static List<Gameroom> gameroomsCreated;
	
	static {
		gameroomsCreated = new LinkedList<Gameroom>();
	}
	
	public WrGameroomNetwork(ClientNetwork clientNetwork) {
		this.clientNetwork = clientNetwork;
	}
	
	public Map sendJoinGameroom (Gameroom gameroom) {
		Map<String, Object> request = new HashMap<String, Object>();
		request.put("mode", "joinGameroom");
		request.put("gameroom", gameroom);
		try {
			clientNetwork.oos.writeObject(request);
			clientNetwork.ois = new ObjectInputStream(clientNetwork.socket.getInputStream());
			return (Map)clientNetwork.ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map sendRefreshGameroomList () {
		Map<String, Object> request = new HashMap<String, Object>();
		request.put("mode", "refreshGameroomList");
		try {
			clientNetwork.oos.writeObject(request);
			clientNetwork.ois = new ObjectInputStream(clientNetwork.socket.getInputStream());
			return (Map)clientNetwork.ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
}
