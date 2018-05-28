package ui.pregame.login;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

import data.user.User;

public class Login_Network {

	String ip;
	int port;
	Socket socket;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	//============================================
	public Login_Network(String ip, int port) throws IOException{
		this.ip = ip;
		this.port = port;
		socket = new Socket(ip, port);
		oos = new ObjectOutputStream(socket.getOutputStream());
	}
	
	
	
	public Map sendJoin(String id, String pass,String nick) {
		Map request = new LinkedHashMap<>();
		User user=new User(id,pass);
		request.put("user", user);
		request.put("mode", "join");
//		request.put("nick", nick);
		try {
			oos.writeObject(request);
			ois = new ObjectInputStream(socket.getInputStream());
			return (Map)ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public Map sendLogin(String id, String pass) {
		Map request = new LinkedHashMap<>();
		User user=new User(id,pass);
		request.put("user", user);
		request.put("mode", "login");
		try {
			oos.writeObject(request);
			ois = new ObjectInputStream(socket.getInputStream());
			return (Map)ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
}
