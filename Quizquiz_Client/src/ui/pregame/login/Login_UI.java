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

public class Login_UI extends JFrame {
	JTextField tfId;
	JPasswordField pfPass;
	JButton btnLogin;
	JButton btnJoin;
	Login_Network net;
	JButton btnPortSet;
	static int s;
	static String ip;
	private JButton btnIpSet;

	public Login_UI() {

		init();
		actionListener();

	}

	void initNetwork() {
		try {
			net = new Login_Network(ip, s);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "서버측과 통신중 에러(-1)");
			System.exit(-1);
		}
	}

	void executeLogin() {
		Map resp = net.sendLogin(tfId.getText(), pfPass.getText());
		int r = (int) resp.get("result");
		if (r == 1002) {
			JOptionPane.showMessageDialog(this, "해당계정이 존재하지않습니다. \n 회원가입을 해주세요");
//		} else if (r == 1) {
//			JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
		} else {
			JOptionPane.showMessageDialog(Login_UI.this, "로그인에 성공하셨습니다. \n 환영합니다.");
		}
	}

	public void actionListener() {
		btnPortSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s = Integer.parseInt(JOptionPane.showInputDialog(Login_UI.this, "설정할 포트를 입력하세요"));

			}
		});
		btnIpSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ip = JOptionPane.showInputDialog(Login_UI.this, "설정할 IP를 입력하세요");

			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initNetwork();
				executeLogin();
				
			}
		});

		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initNetwork();
				new Join_UI(net).setVisible(true);
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				e.getWindow().setVisible(false);
				e.getWindow().dispose();
				System.exit(0);
			}
		});

	}

	public void init() {
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

	public static void main(String[] args) {
		ip = "192.168.10.44";
		s = 12345;
		new Login_UI().setVisible(true);

	}
}
