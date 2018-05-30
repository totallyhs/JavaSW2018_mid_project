package ui.pregame.waitingroom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import data.network.ClientNetworkInfo;
import pregame.waitingroom.CreatRoomNetwork;
import ui.pregame.login.LoginNetwork;

public class CreatRoomUI extends JFrame{
	
	ClientNetworkInfo clientNetwork;
	JRadioButton rdbtnCard;
	JRadioButton rdbtnBingo;
	JButton btnCreate;
	static int s;
	static String ip;
	CreatRoomNetwork net;
	
	
	
	
	public CreatRoomUI(ClientNetworkInfo net) {																		//������
		setResizable(false);
		init();
		clientNetwork = net;
		actionListener();
	}
	
	

	
	public void actionListener() {


		btnCreate.addActionListener(new ActionListener() {														//�� ���� ��ư �׼�
			public void actionPerformed(ActionEvent e) {														
				net = new CreatRoomNetwork(clientNetwork);
				if(rdbtnBingo.isSelected()) {																	//������� ����������� �׼�
					JOptionPane.showMessageDialog(CreatRoomUI.this, "��������� ���� �ϼ̽��ϴ�.");
					net.sendCreate("bingo");													//������� ���� ��ȣ ����
					CreatRoomUI.this.setVisible(false);
					CreatRoomUI.this.dispose();
				}else if(rdbtnCard.isSelected()){																//��ī����� ����������� �׼�
					JOptionPane.showMessageDialog(CreatRoomUI.this, "��ī�� ������ ���� �ϼ̽��ϴ�.");
					net.sendCreate("card");													//��ī����� ���� ��ȣ ����
					CreatRoomUI.this.setVisible(false);
					CreatRoomUI.this.dispose();
				}
				
				
			}
		});

		addWindowListener(new WindowAdapter() {																	//â ���� ����� �ý��� ����
			public void windowClosing(WindowEvent e) {
				e.getWindow().setVisible(false);
				e.getWindow().dispose();
				System.exit(0);
			}
		});

	}

	
	public void init() {																						//UI �ڵ�
		setSize(300,200);
		getContentPane().setLayout(null);
		
		JLabel lbSelectGame = new JLabel("\uAC8C\uC784 \uC120\uD0DD");
		lbSelectGame.setHorizontalAlignment(SwingConstants.CENTER);
		lbSelectGame.setBounds(51, 10, 180, 35);
		getContentPane().add(lbSelectGame);
		
		rdbtnBingo = new JRadioButton("\uBE59\uACE0\uAC8C\uC784");
		rdbtnBingo.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnBingo.setBounds(8, 74, 121, 23);
		getContentPane().add(rdbtnBingo);
		
		rdbtnCard = new JRadioButton("\uC6D0\uCE74\uB4DC");
		rdbtnCard.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnCard.setEnabled(false);
		rdbtnCard.setBounds(155, 74, 121, 23);
		getContentPane().add(rdbtnCard);
		
		btnCreate = new JButton("\uBC29 \uB9CC\uB4E4\uAE30");
		btnCreate.setBounds(93, 128, 97, 23);
		getContentPane().add(btnCreate);
	}
}
