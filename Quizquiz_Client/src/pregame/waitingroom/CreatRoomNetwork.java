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

	public static int SUCCESS = 1031;									//방 생성 성공코드 1031
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
			JOptionPane.showMessageDialog(waitingRoomUI, "게임방 생성 성공");
			waitingRoomUI.dispose();
			waitingRoomUI.setVisible(false);
			
			// 게임방에 들언간다
			/*
			 * 
			 * 
			 * 
			 */
		} else if (result == FAILED) {
			JOptionPane.showMessageDialog(waitingRoomUI, "게임방 생설 실패");
		}
	}
	
	
	
	
	
	
	
	
	public CreatRoomNetwork(ClientNetworkInfo clientNetwork) {					//생성자
		this.clientNetwork = clientNetwork;

	}


	public boolean sendCreate(String game) {								//게임생성 신호 메소드
		Map<String, Object> request = new LinkedHashMap<>();
		request.put("mode", "createRoom");
		request.put("game", game);

		try {
			clientNetwork.oos.writeObject(request);											//신호 전송
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;

		}
	}

}
