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
	
	// 로그인 성공 및 실패
	public static int SUCCESS = 2011;
	public static int FAILED = 2012;

	// 리스너를 위한 생성자
	public WrGameroomJoinNetwork(ClientNetworkInfo clientNetwork, Map response, WaitingRoomUI waitingRoomUI) {
		this.clientNetwork = clientNetwork;
		this.response = response;
		this.waitingRoomUI = waitingRoomUI;
	}
	
	// 요청을 보내고 서버로부터 답장을 받는 쓰레드
	@Override
	public void run() {
		int result = (int) response.get("result");
		if (result == SUCCESS) {
			JOptionPane.showMessageDialog(waitingRoomUI, "게임방 조인 성공");
			waitingRoomUI.dispose();
			waitingRoomUI.setVisible(false);
		} else if (result == FAILED) {
			JOptionPane.showMessageDialog(waitingRoomUI, "게임방 조인 실패");
		}
		
	}

	
	// 요청할대 사용되는 생성자
	public WrGameroomJoinNetwork(ClientNetworkInfo clientNetwork) {
		this.clientNetwork = clientNetwork;
	}
	
	// 게임방에 참가하고 싶을때 서버에 요청을 보낼때 사용되는 메소드.
	// UI에서 불러온다
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
