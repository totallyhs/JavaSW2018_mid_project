package pregame.waitingroom;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import data.gameroom.Gameroom;
import data.network.ClientNetworkInfo;
import ui.pregame.waitingroom.WaitingRoomUI;
import ui.pregame.waitingroom.WrGameroomDisplay;

public class WrGameroomJoinNetwork extends Thread {
	ClientNetworkInfo clientNetwork;
	Map response;
	WaitingRoomUI waitingRoomUI;
	
	// �α��� ���� �� ����
	public static int SUCCESS = 2011;
	public static int FAILED = 2012;

	// �����ʸ� ���� ������
	public WrGameroomJoinNetwork(ClientNetworkInfo clientNetwork, Map response, WaitingRoomUI waitingRoomUI) {
		this.clientNetwork = clientNetwork;
		this.response = response;
		this.waitingRoomUI = waitingRoomUI;
	}
	
	// ��û�� ������ �����κ��� ������ �޴� ������
	@Override
	public void run() {
		int result = (int) response.get("result");
		if (result == SUCCESS) {
			JOptionPane.showMessageDialog(waitingRoomUI, "���ӹ� ���� ����");
			waitingRoomUI.dispose();
			waitingRoomUI.setVisible(false);
		} else if (result == FAILED) {
			JOptionPane.showMessageDialog(waitingRoomUI, "���ӹ� ���� ����");
		}
		
	}

	
	// ��û�Ҵ� ���Ǵ� ������
	public WrGameroomJoinNetwork(ClientNetworkInfo clientNetwork) {
		this.clientNetwork = clientNetwork;
	}
	
	// ���ӹ濡 �����ϰ� ������ ������ ��û�� ������ ���Ǵ� �޼ҵ�.
	// UI���� �ҷ��´�
	public boolean sendJoinGameroom (Gameroom gameroom) {
		Map<String, Object> request = new HashMap<String, Object>();
		request.put("mode", "joinGameroom");
		request.put("gameroom", gameroom);
		try {
			clientNetwork.oos.writeObject(request);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
