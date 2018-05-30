package data.gameroom;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import data.network.ClientNetworkInfo;

public class Gameroom implements Serializable {
	public String title;
	
	public static int number;
	public int maxPlayers;
	
	public boolean isFull;
	public boolean isStarted;
	
	public List<ClientNetworkInfo> joinedUsers;
	
	
	public Gameroom(int maxPlayers) {
		this.maxPlayers = maxPlayers;
		joinedUsers = new LinkedList<ClientNetworkInfo>();
		isFull = false;
		isStarted = false;
		
		number++;
	}
	
	@Override
	public int hashCode() {
		return number;
	}

	@Override
	public boolean equals(Object obj) {
		Gameroom otherGameroom = (Gameroom) obj;
		if (this.number == otherGameroom.number) {
			return true;
		} else {
			return false;
		}
	}
	

	
}
