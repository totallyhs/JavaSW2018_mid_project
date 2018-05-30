package ui.pregame.waitingroom;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import data.gameroom.Gameroom;
import data.manager.ClientListManager;
import data.network.ClientNetworkInfo;
import pregame.waitingroom.WrGameroomJoinNetwork;

public class WrGameroomDisplay extends JPanel {
	ClientNetworkInfo clientNetwork;
	WaitingRoomUI waitingRoomUI;
	
	List<GameroomBtn> gamerooms;
	
	public WrGameroomDisplay(ClientNetworkInfo clientNetwork, WaitingRoomUI waitingRoomUI) {
		this.clientNetwork = clientNetwork;
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		gamerooms = new ArrayList<GameroomBtn>();
		
		this.waitingRoomUI = waitingRoomUI;
		
		initFrame();
		actionListener();
		
	}
	
	private void actionListener() {
		
		for (int i=0; i<gamerooms.size(); i++) {
			GameroomBtn roomBtn = gamerooms.get(i);
			roomBtn.setActionCommand("Gameroom/" + String.valueOf(i));
			this.add(roomBtn);
			roomBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						WrGameroomJoinNetwork wrgn = new WrGameroomJoinNetwork(clientNetwork);
						boolean b = wrgn.sendJoinGameroom(roomBtn.gameroom);
					}
					
				}
				
			});
		}
			
	}
	
	
	
	public void refresh(List<Gameroom> cGRL) {
		System.out.println(clientNetwork.user.id + " refresh method");
		ClientListManager.gameroomsCreated = new LinkedList<Gameroom>();
		System.out.println(clientNetwork.user.id + " gameroom size " + cGRL.size());
		for (int i=0; i<cGRL.size(); i++) {
			GameroomBtn gb = new GameroomBtn(cGRL.get(i));
			gamerooms.add(gb);
			ClientListManager.gameroomsCreated.add(cGRL.get(i));
		}
	}
	
	private void initFrame() {
		setSize(960, 360);

//		gamerooms.add(new GameroomBtn(new Gameroom(3)));					//test
//		
//		gamerooms.add(new GameroomBtn(new Gameroom(3)));
//		gamerooms.add(new GameroomBtn("2"));
//		gamerooms.add(new GameroomBtn("3"));
//		gamerooms.add(new GameroomBtn("4"));
//		gamerooms.add(new GameroomBtn("5"));
//		gamerooms.add(new GameroomBtn("6"));
//		gamerooms.add(new GameroomBtn("7"));
		
		
	}
	
//	public static void main(String[] args) {								//test
//		WrGameroomDisplay display = new WrGameroomDisplay(clientNetwork);
//	       
//        JFrame frame = new JFrame();
//        
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(960, 360);
//
//        Container contentPane = frame.getContentPane();
//        contentPane.add(display);
//
//        frame.setVisible(true);
//	}
}
