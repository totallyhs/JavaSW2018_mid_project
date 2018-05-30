package ui.pregame.waitingroom;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.gameroom.Gameroom;
import data.user.User;
import javax.swing.SwingConstants;

public class GameroomBtn extends JButton {
	Gameroom gameroom;
	public GameroomBtn(Gameroom gameroom) {
		this.gameroom = gameroom;
		initFrame();
	}
	

	public void initFrame() {
		JPanel p = new JPanel();
		p.setLayout(null);
		setPreferredSize(new Dimension(460, 100));
		
		this.add(p);
		
		JLabel grNumber = new JLabel(String.valueOf(gameroom.number));
		grNumber.setBounds(12, 0, 55, 18);
		p.add(grNumber);
		
		JLabel grTitle = new JLabel(gameroom.title);
		grTitle.setVerticalAlignment(SwingConstants.TOP);
		grTitle.setBounds(12, 27, 428, 34);
		p.add(grTitle);
		
		JLabel grSize = new JLabel(String.valueOf(gameroom.joinedUsers.size()) + "/" + String.valueOf(gameroom.maxPlayers));
		grSize.setVerticalAlignment(SwingConstants.TOP);
		grSize.setBounds(392, 0, 48, 18);
		p.add(grSize);
		this.setMargin(new Insets(1, 1, 1, 1));
	}
	
	
//	public static void main(String[] args) {					//test
//			Gameroom gr = new Gameroom(3);
//	       User user = new User();
//	       gr.joinedUsers.add(user);
//	       gr.title = "okokokooook";
//	       GameroomBtn button = new GameroomBtn(gr);
//	       
//	       
//	        JFrame frame = new JFrame();
//	        
//	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	        frame.setSize(460, 100);
//
//	        Container contentPane = frame.getContentPane();
//	        contentPane.add(button);
//
//	        frame.setVisible(true);
//
//	}
}
