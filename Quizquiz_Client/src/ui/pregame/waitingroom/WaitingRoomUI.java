package ui.pregame.waitingroom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.network.ClientNetworkInfo;

public class WaitingRoomUI extends JFrame{
	JButton btnCreateRoom;
	static int s;
	static String ip;
	static ClientNetworkInfo clientNetwork;
	public WrGameroomDisplay ������;
	public JPanel ���������;
	public JPanel ä�ù�1;
	
	public WaitingRoomUI(ClientNetworkInfo net){										//������
		
		init();
		clientNetwork = net;
		actionListener();

	}
	

	
	public void actionListener() {													//�׼�ó�� ���� �޼ҵ�

		btnCreateRoom.addActionListener(new ActionListener() {						//�� ���� ��ư �׼�
			public void actionPerformed(ActionEvent e) {
				new CreatRoomUI(clientNetwork).setVisible(true);					//CreatRoomUI ����
			}
		});



		addWindowListener(new WindowAdapter() {										//â ���� ����� �ý��� ����
			public void windowClosing(WindowEvent e) {
				e.getWindow().setVisible(false);
				e.getWindow().dispose();
				System.exit(0);
			}
		});

	}

	
	
	
	public void init() {
		setSize(1000,700);
		getContentPane().setLayout(null);
		
		btnCreateRoom = new JButton("\uBC29 \uB9CC\uB4E4\uAE30");
		btnCreateRoom.setBounds(741, 10, 231, 36);
		getContentPane().add(btnCreateRoom);
		
		������ = new WrGameroomDisplay(clientNetwork, this);
		������.setBounds(12, 53, 960, 360);
		getContentPane().add(������);
		
		��������� = new JPanel();
		���������.setBounds(722, 451, 250, 200);
		getContentPane().add(���������);
		
//		JPanel ä�ù� = new JPanel();
		ä�ù�1 = new WaitingroomChatUI(clientNetwork);
//		ä�ù�1.setUI(this);
		
		ä�ù�1.setBounds(12, 451, 650, 200);
		getContentPane().add(ä�ù�1);

	}
//	public static void main(String[] args) {								//test
//		new WaitingRoomUI(clientNetwork).setVisible(true);
//	}
	
}
