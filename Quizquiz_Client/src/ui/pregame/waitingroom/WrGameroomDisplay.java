package ui.pregame.waitingroom;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import data.network.ClientNetwork;
import pregame.waitingroom.WrGameroomNetwork;

public class WrGameroomDisplay extends JPanel {
	static ClientNetwork clientNetwork;
	
	List<GameroomBtn> gamerooms;
	
	public WrGameroomDisplay(ClientNetwork clientNetwork) {
		this.clientNetwork = clientNetwork;
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		gamerooms = new ArrayList<GameroomBtn>();
		
		initFrame();
		actionListener();
		
	}
	
	private void actionListener() {
		for (int i=0; i<gamerooms.size(); i++) {
			gamerooms.get(i).setActionCommand("Gameroom/" + String.valueOf(i));
			this.add(gamerooms.get(i));
			gamerooms.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						WrGameroomNetwork wrgn = new WrGameroomNetwork(clientNetwork);
						Map response = wrgn.sendJoinGameroom(gamerooms.get(1).gameroom);
						
						int result = (int) response.get("result");
						if (result == 2011) {
							JOptionPane.showMessageDialog(WrGameroomDisplay.this, "게임방 조인 성공");
						} else if (result == 2012) {
							JOptionPane.showMessageDialog(WrGameroomDisplay.this, "게임방 조인 실패");
							refresh();
						}
						
					}
					
				}
				
			});
		}
			
	}
	
	
	
	private void refresh() {
		for (int i=0; i<WrGameroomNetwork.gameroomsCreated.size(); i++) {
			GameroomBtn gb = new GameroomBtn(WrGameroomNetwork.gameroomsCreated.get(i));
			gamerooms.add(gb);
		}
	}
	
	private void initFrame() {
		setSize(960, 360);
		
//		gamerooms.add(new GameroomBtn("1"));
//		gamerooms.add(new GameroomBtn("2"));
//		gamerooms.add(new GameroomBtn("3"));
//		gamerooms.add(new GameroomBtn("4"));
//		gamerooms.add(new GameroomBtn("5"));
//		gamerooms.add(new GameroomBtn("6"));
//		gamerooms.add(new GameroomBtn("7"));
		
		
	}
	
	public static void main(String[] args) {
		WrGameroomDisplay display = new WrGameroomDisplay(clientNetwork);
	       
        JFrame frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960, 360);

        Container contentPane = frame.getContentPane();
        contentPane.add(display);

        frame.setVisible(true);
	}
}
