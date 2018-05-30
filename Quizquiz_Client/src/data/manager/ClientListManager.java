package data.manager;

import java.util.LinkedList;
import java.util.List;

import data.gameroom.Gameroom;

public class ClientListManager {
	public static List<Gameroom> gameroomsCreated;	
	static {
		gameroomsCreated = new LinkedList<Gameroom>();
	}
}
