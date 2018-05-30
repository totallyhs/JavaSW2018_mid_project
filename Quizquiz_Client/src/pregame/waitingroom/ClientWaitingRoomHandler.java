package pregame.waitingroom;

import java.io.IOException;
import java.util.Map;

import data.network.ClientNetworkInfo;
import ui.pregame.waitingroom.WaitingRoomUI;

public class ClientWaitingRoomHandler extends Thread {
	ClientNetworkInfo net;
	WaitingRoomUI waitingRoomUI;

	public ClientWaitingRoomHandler(ClientNetworkInfo net, WaitingRoomUI waitingRoomUI) {
		this.net = net;
		this.waitingRoomUI = waitingRoomUI;
	}
	
	
	// 서버 데이터 듣는 객체
	private Map responseListener(Map response) {
		try {
			response = (Map)net.ois.readObject();
			System.out.println("[CLIENT WAITINGROOM DATA HANDLER] response received");
			return response;
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return response;
		}
	}
	
	// 데이터 처리
	@Override
	public void run() {
		if (true) {
			Map response = null;
			while (net.socket.isBound()) {
				System.out.println(net.user.id + "[CLIENT WAITINGROOM DATA HANDLER] listening");
				response = responseListener(response);
				String mode = (String)response.get("mode");
				System.out.println("[CLIENT WAITINGROOM DATA HANDLER] mode " + mode);
				switch(mode) {
//				case "chat":
//					WaitingromChatNetwork wCN = new WaitingromChatNetwork(net, response);
//					cs.start();
//					break;
				case "joinGameroom":
					WrGameroomJoinNetwork gjn = new WrGameroomJoinNetwork(net, response, waitingRoomUI);
					gjn.start();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "refreshGameroomList":
					WrGameroomRefreshNetwork grn = new WrGameroomRefreshNetwork(net, response, waitingRoomUI);
					grn.start();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "createGameRoom":
					CreatRoomNetwork crn = new CreatRoomNetwork(net, response, waitingRoomUI);
					crn.start();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
			
			
		}
	}
	
}
