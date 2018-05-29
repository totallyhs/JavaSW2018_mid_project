package ui.pregame.waitingroom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import data.network.ClientNetwork;
import data.user.User;

public class CreatRoomNetwork {

	ClientNetwork net;

	public CreatRoomNetwork(ClientNetwork net) throws IOException {					//������
		this.net = net;

	}

	public Map sendCreate(String mode, String game) {								//���ӻ��� ��ȣ �޼ҵ�
		Map request = new LinkedHashMap<>();
		request.put("mode", mode);
		request.put("game", game);

		try {
			net.oos.writeObject(request);											//��ȣ ����
			net.ois = new ObjectInputStream(net.socket.getInputStream());			//��ȣ ����
			return (Map) net.ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;

		}
	}

}
