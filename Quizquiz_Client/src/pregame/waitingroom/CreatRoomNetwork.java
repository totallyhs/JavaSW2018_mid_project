package pregame.waitingroom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import data.network.ClientNetworkInfo;
import data.user.User;
import ui.pregame.waitingroom.WaitingRoomUI;

public class CreatRoomNetwork extends Thread {

	ClientNetworkInfo clientNetwork;
	Map response;
	WaitingRoomUI waitingRoomUI;

	public static int SUCCESS = 1031;									//�� ���� �����ڵ� 1031
	public static int FAILED = 1032;
	
	
	public CreatRoomNetwork(ClientNetworkInfo clientNetwork, Map response, WaitingRoomUI waitingRoomUI) {
		this.clientNetwork = clientNetwork;
		this.response = response;
		this.waitingRoomUI = waitingRoomUI;
	}
	

	@Override
	public void run() {
		int result = (int) response.get("result");
		if (result == SUCCESS) {
			JOptionPane.showMessageDialog(waitingRoomUI, "���ӹ� ���� ����");
			waitingRoomUI.dispose();
			waitingRoomUI.setVisible(false);
			
			// ���ӹ濡 ��𰣴�
			/*
			 * 
			 * 
			 * 
			 */
		} else if (result == FAILED) {
			JOptionPane.showMessageDialog(waitingRoomUI, "���ӹ� ���� ����");
		}
	}
	
	
	
	
	
	
	
	
	public CreatRoomNetwork(ClientNetworkInfo clientNetwork) {					//������
		this.clientNetwork = clientNetwork;

	}


	public boolean sendCreate(String game) {								//���ӻ��� ��ȣ �޼ҵ�
		Map<String, Object> request = new LinkedHashMap<>();
		request.put("mode", "createRoom");
		request.put("game", game);

		try {
			clientNetwork.oos.writeObject(request);											//��ȣ ����
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;

		}
	}

}
