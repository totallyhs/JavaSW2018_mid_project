package ui.pregame.login;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

import data.network.ClientNetworkInfo;
import data.user.User;

public class LoginNetwork {

	ClientNetworkInfo net;
	//============================================
	public LoginNetwork(ClientNetworkInfo net) throws IOException{							//생성자
		this.net = net;
	}
	
	
	
	public Map sendJoin(String id, String pass,String nick) {							//회원가입 전송 메소드
		Map request = new LinkedHashMap<>();
		User user=new User(id,pass);
		request.put("user", user);
		request.put("mode", "join");
//		request.put("nick", nick);
		try {
			net.oos.writeObject(request);
			return (Map)net.ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
			
		} 
	}
	
	public Map sendLogin(String id, String pass) {										//로그인 전송 메소드
		Map request = new LinkedHashMap<>();
		User user=new User(id,pass);
		request.put("user", user);
		request.put("mode", "login");
		try {
			net.oos.writeObject(request);
			return (Map)net.ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
}
