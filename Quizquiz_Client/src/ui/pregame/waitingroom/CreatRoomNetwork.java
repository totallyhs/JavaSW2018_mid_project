package ui.pregame.waitingroom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import data.network.ClientNetwork;
import data.user.User;

public class CreatRoomNetwork {

	ClientNetwork net;

	public CreatRoomNetwork(ClientNetwork net) throws IOException {					//생성자
		this.net = net;

	}

	public Map sendCreate(String mode, String game) {								//게임생성 신호 메소드
		Map request = new LinkedHashMap<>();
		request.put("mode", mode);
		request.put("game", game);

		try {
			net.oos.writeObject(request);											//신호 전송
			net.ois = new ObjectInputStream(net.socket.getInputStream());			//신호 수신
			return (Map) net.ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;

		}
	}

}
