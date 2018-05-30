package game.bingo;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BingoRoomUI extends JFrame{
	private JPanel panelBingo;
	private JPanel panelUserOwn;
	private JPanel panelUser2;
	private JPanel panelUser3;
	private JPanel panelChat;

	public BingoRoomUI() {											//»ý¼ºÀÚ
		
		
		
		
	}
	
	
	
	
	
	public void init() {											//UI
		setResizable(false);
		setSize(1000,700);
		getContentPane().setLayout(null);
		
		panelBingo = new JPanel();
		panelBingo.setBounds(99, 40, 300, 300);
		getContentPane().add(panelBingo);
		
		panelUserOwn = new JPanel();
		panelUserOwn.setBounds(148, 422, 200, 200);
		getContentPane().add(panelUserOwn);
		
		panelUser2 = new JPanel();
		panelUser2.setBounds(487, 89, 200, 200);
		getContentPane().add(panelUser2);
		
		panelUser3 = new JPanel();
		panelUser3.setBounds(749, 89, 200, 200);
		getContentPane().add(panelUser3);
		
		panelChat = new JPanel();
		panelChat.setBounds(487, 422, 463, 200);
		getContentPane().add(panelChat);
	}
}
