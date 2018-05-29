package manager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Hee Sang Shin
 * @version 1.0
 * @date 2018.05.29
 * 
 * 새로운 클라이언트가 서버에 접속했을 경우 요청을 처리해주는 메인서버
 *
 */

public class MainServer {
	
	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(45678);
			ExistingUser.uploadUsers();
			System.out.println("[SERVER] server opened");
		} catch (IOException e) {
			System.out.println("[SERVER] server opening failed");
			e.printStackTrace();
		}
		while (server.isBound()) {
			try {
				System.out.println("[SERVER] listen to client's connection");				
				Socket socket = server.accept();
				System.out.println("[SERVER] new client accepted");
				LoginRequestHandler sub = new LoginRequestHandler(socket);
				sub.start();
			} catch (IOException e) {
				System.out.println("[SERVER] failed to accept a new client");
				e.printStackTrace();
			}
			
		}
	}
}
