package data.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import data.user.User;

public class ClientNetworkInfo {
	public User user;
	public static String ip;
	public static int port;
	public Socket socket;
	
	public ObjectOutputStream oos;
	public ObjectInputStream ois;
	
	//============================================
	
	
	public ClientNetworkInfo(String ip, int port){
		this.ip = ip;
		this.port = port;
		try {
			socket = new Socket(ip, port);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			socket = null;
			oos = null;
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		ClientNetworkInfo otherNetInfo = (ClientNetworkInfo) obj;
		if (user.equals(otherNetInfo.user)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return user.hashCode();
	}
}
