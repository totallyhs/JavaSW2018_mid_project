
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
	
	
	// ������Ŷ�� ���÷� ���ò��� (��û�� �����߸� ���»�Ȳ�� �ƴ�)
		public void run() {
			while(socket.isBound()) {
				
				try {
					Map response = (Map)net.ois.readObject();
					System.out.println("[Client] " + response);
					// ������ ���� �̰� ������ ���⼭ UI ������ ���Ѿ� ��.
					String mode =(String)response.get("mode");
					switch(mode) {
					case "chat" :
						chatHandle(response);	// ��� ����ڿ��� ������ ����
						break;
//					case "exit" :
//						exitHandle(response);	// ��� ����ڿ��� ������ ����
//						break;
//					case "change" :
//						changeHandle(response);	// ��û���� ����ڿ��� ������ ����
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
