package data.gameroom;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import data.user.User;

public class Gameroom implements Serializable {
	public String title;
	
	public int number;
	public int maxPlayers;
	
	public boolean isFull;
	
	public List<User> joinedUsers;
	
	{
		number++;
	}
	
	public Gameroom(int maxPlayers) {
		this.maxPlayers = maxPlayers;
		joinedUsers = new LinkedList<User>();
		isFull = false;
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
