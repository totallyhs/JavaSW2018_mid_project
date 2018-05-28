package pregame.login;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import data.user.User;
import manager.ExistingUser;

public class SignUpServer extends Thread {
		// 회원가입 성공 및 실패
		public static int SUCCESS = 1011;
		public static int FAILED = 1012;
		
		// 네트워크 연결
		public Socket socket;
		public ObjectOutputStream oos;
		public Map<String, Object> request;
		
		// 생성자 - 기본 세팅
		public SignUpServer(Socket socket, Map request) {
			try {
				this.socket = socket;
				oos = new ObjectOutputStream(socket.getOutputStream());
				this.request = request;
			} catch (IOException e) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
		
		// 쓰레드
		@Override
		public void run() {
			while (socket.isBound()) {
				signUp(request);
				break;
			}
		}

		// 회원가입 메소드
		/* 
		 * 회원가입시 :
		 * 
		 */
		private void signUp(Map request) {
			User user = (User) request.get("user");
			Map<String, Object> response = new HashMap<String, Object>();
			response.put("mode", "join");
			if (ExistingUser.existingUsers.contains(user)) {
				response.put("result", FAILED);
			} else {
				response.put("result", SUCCESS);
			}
			try {
				oos.writeObject(response);
				if ((int) response.get("result") == SUCCESS) {					
					ExistingUser.existingUsers.add(user);
					ExistingUser.saveUsers();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
