package ui.pregame.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import data.network.ClientNetwork;
import ui.pregame.waitingroom.WaitingRoomUI;

public class Login_UI extends JFrame {
	
	ClientNetwork clientNetwork;
	
	JTextField tfId;
	JPasswordField pfPass;
	JButton btnLogin;
	JButton btnJoin;
	Login_Network net;
	JButton btnPortSet;
	static int s;
	static String ip;
	private JButton btnIpSet;

	public Login_UI() {																				//생성자

		init();
		actionListener();

	}

	void initNetwork() {																			//서버와 연결 메소드
		clientNetwork = new ClientNetwork(ip,s);
		try {
			net = new Login_Network(clientNetwork);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "서버측과 통신중 에러(-1)");
			System.exit(-1);
		}
	}

	void executeLogin() {																			//로그인 메소드
		Map resp = net.sendLogin(tfId.getText(), pfPass.getText());
		int r = (int) resp.get("result");
		if (r == 1002) {
			JOptionPane.showMessageDialog(this, "해당계정이 존재하지않습니다. \n 회원가입을 해주세요");
//		} else if (r == 1) {
//			JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
		} else {
			JOptionPane.showMessageDialog(Login_UI.this, "로그인에 성공하셨습니다. \n 환영합니다.");
			new WaitingRoomUI(clientNetwork).setVisible(true);								//기존 창을 끄고 WaitingRoomUI 실행
			Login_UI.this.setVisible(false);
			Login_UI.this.dispose();
		}
	}

	public void actionListener() {
		btnPortSet.addActionListener(new ActionListener() {											//포트설정 버튼 액션
			public void actionPerformed(ActionEvent e) {
				s = Integer.parseInt(JOptionPane.showInputDialog(Login_UI.this, "설정할 포트를 입력하세요"));

			}
		});
		btnIpSet.addActionListener(new ActionListener() {											//IP설정 버튼 액션
			public void actionPerformed(ActionEvent e) {
				ip = JOptionPane.showInputDialog(Login_UI.this, "설정할 IP를 입력하세요");

			}
		});
		btnLogin.addActionListener(new ActionListener() {											//로그인 버튼 액션
			public void actionPerformed(ActionEvent e) {
				initNetwork();																		//서버와 연결
				executeLogin();																		//로그인 메소드 실행
				
			}
		});

		btnJoin.addActionListener(new ActionListener() {											//회원가입 버튼 액션
			public void actionPerformed(ActionEvent e) {
				initNetwork();																		//서버와 연결
				new Join_UI(net).setVisible(true);													//Join_UI창 띄우기
			}
		});

		addWindowListener(new WindowAdapter() {														//창을 강제로 종료할경우 시스템 종료
			public void windowClosing(WindowEvent e) {
				e.getWindow().setVisible(false);
				e.getWindow().dispose();
				System.exit(0);
			}
		});

	}

	public void init() {																			//UI 코드
		setSize(1000, 700);
		getContentPane().setLayout(null);

		JLabel lbID = new JLabel("ID");
		lbID.setHorizontalAlignment(SwingConstants.CENTER);
		lbID.setBounds(237, 302, 214, 42);
		getContentPane().add(lbID);

		JLabel lbpass = new JLabel("PASSWORD");
		lbpass.setHorizontalAlignment(SwingConstants.CENTER);
		lbpass.setBounds(237, 355, 214, 42);
		getContentPane().add(lbpass);

		tfId = new JTextField();
		tfId.setBounds(502, 313, 116, 21);
		getContentPane().add(tfId);
		tfId.setColumns(10);

		pfPass = new JPasswordField();
		pfPass.setBounds(502, 366, 116, 21);
		getContentPane().add(pfPass);

		btnLogin = new JButton("\uB85C\uADF8\uC778");
		btnLogin.setBounds(502, 418, 116, 34);
		getContentPane().add(btnLogin);

		btnJoin = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnJoin.setBounds(297, 418, 97, 34);
		getContentPane().add(btnJoin);
		
		btnPortSet = new JButton("\uD3EC\uD2B8 \uC138\uD305");
		btnPortSet.setBounds(815, 28, 130, 23);
		getContentPane().add(btnPortSet);
		
		btnIpSet = new JButton("IP \uC138\uD305");
		btnIpSet.setBounds(815, 74, 130, 21);
		getContentPane().add(btnIpSet);

	}

	public static void main(String[] args) {									//메인 함수
		ip = "192.168.10.44";													//기본 설정 IP
		s = 45678;																//기본 설정 포트
		new Login_UI().setVisible(true);										//Login_UI 실행

	}
}
