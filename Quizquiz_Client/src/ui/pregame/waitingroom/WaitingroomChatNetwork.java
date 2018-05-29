
package ui.pregame.waitingroom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

import data.network.ClientNetwork;

public class WaitingroomChatNetwork extends Thread {
	
	ClientNetwork net;
	Socket socket;
	WaitingroomChatUI ui;
//	
	public void setUi(WaitingroomChatUI ui)	{
		this.ui = ui;
	}
	

	public WaitingroomChatNetwork(ClientNetwork clientNetwork) throws IOException{
		net = clientNetwork;
	}
	
	public void sendChat(String message) {
		Map request = new LinkedHashMap<>();
		request.put("mode", "chat");
		request.put("message", message);
		try {
			net.oos.writeObject(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//==============================
	
	
	// 응답패킷이 수시로 들어올꺼라서 (요청을 보내야만 오는상황이 아님)
		public void run() {
			while(socket.isBound()) {
				
				try {
					Map response = (Map)net.ois.readObject();
					System.out.println("[Client] " + response);
					// 응답이 오면 이걸 가지고 여기서 UI 변경을 시켜야 함.
					String mode =(String)response.get("mode");
					switch(mode) {
					case "chat" :
						chatHandle(response);	// 모든 사용자에게 응답을 전송
						break;
//					case "exit" :
//						exitHandle(response);	// 모든 사용자에게 응답을 전송
//						break;
//					case "change" :
//						changeHandle(response);	// 요청보낸 사용자에게 응답을 전송
//						break;
					}
				} catch(Exception e) {
					e.printStackTrace();
					break;
				}
			}
		}
		
		private void chatHandle(Map response) {
			String message = (String)response.get("message");
			String talker = (String)response.get("talker");
		//	String nval =ui.taChatLog.getText() + "["+talker+"] " + message+"\n";
		//	ui.taChatLog.setText(nval);
			ui.taChatLog.append("["+talker+"] " + message+"\n");
			// System.out.println(ui.taChatLog.getCaretPosition());
			ui.taChatLog.setCaretPosition(ui.taChatLog.getText().length());
		}
		
}
