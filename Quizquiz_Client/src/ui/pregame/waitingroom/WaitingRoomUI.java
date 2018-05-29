package ui.pregame.waitingroom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.network.ClientNetwork;

public class WaitingRoomUI extends JFrame{
	JButton btnCreateRoom;
	static int s;
	static String ip;
	ClientNetwork clientNetwork;
	
	public WaitingRoomUI(ClientNetwork net){										//생성자
		
		init();
		clientNetwork = net;
		actionListener();
		
		
	}
	

	
	public void actionListener() {													//액션처리 모음 메소드

		btnCreateRoom.addActionListener(new ActionListener() {						//방 생성 버튼 액션
			public void actionPerformed(ActionEvent e) {
				new CreatRoomUI(clientNetwork).setVisible(true);					//CreatRoomUI 실행
			}
		});



		addWindowListener(new WindowAdapter() {										//창 강제 종료시 시스템 종료
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
		
		JPanel 대기방목록 = new JPanel();
		대기방목록.setBounds(12, 53, 960, 360);
		getContentPane().add(대기방목록);
		
		JPanel 사용자정보 = new JPanel();
		사용자정보.setBounds(722, 451, 250, 200);
		getContentPane().add(사용자정보);
		
//		JPanel 채팅방 = new JPanel();
		JPanel 채팅방1 = new WaitingroomChatUI(clientNetwork);
		채팅방1.setUI(this);
		
		채팅방1.setBounds(12, 451, 650, 200);
		getContentPane().add(채팅방1);

	}
}
