package ui.pregame.waitingroom;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.Font;

public class CreatRoom extends JFrame{
	private JRadioButton rdbtnCard;
	private JRadioButton rdbtnBingo;
	private JRadioButton rdbtnTwoPeople;
	private JRadioButton rdbtnThreePeople;
	public CreatRoom() {
		setSize(300,300);
		getContentPane().setLayout(null);
		
		JLabel lbSelectGame = new JLabel("\uAC8C\uC784 \uC120\uD0DD");
		lbSelectGame.setHorizontalAlignment(SwingConstants.CENTER);
		lbSelectGame.setBounds(51, 22, 180, 35);
		getContentPane().add(lbSelectGame);
		
		JLabel lbSelectPeople = new JLabel("\uBC29 \uC778\uC6D0\uC218");
		lbSelectPeople.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lbSelectPeople.setHorizontalAlignment(SwingConstants.CENTER);
		lbSelectPeople.setBounds(51, 115, 180, 35);
		getContentPane().add(lbSelectPeople);
		
		rdbtnBingo = new JRadioButton("\uBE59\uACE0\uAC8C\uC784");
		rdbtnBingo.setBounds(8, 74, 121, 23);
		getContentPane().add(rdbtnBingo);
		
		rdbtnCard = new JRadioButton("\uC6D0\uCE74\uB4DC");
		rdbtnCard.setBounds(155, 74, 121, 23);
		getContentPane().add(rdbtnCard);
		
		rdbtnTwoPeople = new JRadioButton("2\uC778");
		rdbtnTwoPeople.setBounds(8, 189, 121, 23);
		getContentPane().add(rdbtnTwoPeople);
		
		rdbtnThreePeople = new JRadioButton("3\uC778");
		rdbtnThreePeople.setBounds(155, 189, 121, 23);
		getContentPane().add(rdbtnThreePeople);
	}
}
