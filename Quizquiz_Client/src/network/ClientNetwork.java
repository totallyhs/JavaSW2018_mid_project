package data.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientNetwork {
	public static String ip;
	public static int port;
	public Socket socket;
	
	public ObjectOutputStream oos;
	public ObjectInputStream ois;
	
	//============================================
	
	
	private static ClientNetwork net = new ClientNetwork(ip, port);
	
	private ClientNetwork(String ip, int port){
		this.ip = ip;
		this.port = port;
		try {
			socket = new Socket(ip, port);
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			socket = null;
			oos = null;
			e.printStackTrace();
		}
		net = this;
	}
	
	synchronized public static ClientNetwork getInstance() {
		return net;
	}
}
