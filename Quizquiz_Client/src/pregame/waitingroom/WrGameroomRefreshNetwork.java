package pregame.waitingroom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import data.gameroom.Gameroom;
import data.network.ClientNetworkInfo;
import ui.pregame.waitingroom.WaitingRoomUI;

public class WrGameroomRefreshNetwork extends Thread {
	ClientNetworkInfo clientNetwork;
	Map response;
	WaitingRoomUI waitingRoomUI;
	
	// �����ʸ� ���� ������
	public WrGameroomRefreshNetwork(ClientNetworkInfo clientNetwork, Map response, WaitingRoomUI waitingRoomUI) {
		this.clientNetwork = clientNetwork;
		this.response = response;
		this.waitingRoomUI = waitingRoomUI;
	}
	
	@Override
	public void run() {
		boolean result = (boolean) response.get("result");
		if (result) {
			List<Gameroom> cGRL = (List<Gameroom>) response.get("gameroomList");
			waitingRoomUI.������.refresh(cGRL);
		}		
	}
	

	
	
	
	
	
	// REFRESH ��ư�� ���� ���������� ����� ��
	/*
	public boolean sendRefreshGameroomList () {
		Map<String, Object> request = new HashMap<String, Object>();
		request.put("mode", "refreshGameroomList");
		try {
			clientNetwork.oos.writeObject(request);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	*/
	
	
}
