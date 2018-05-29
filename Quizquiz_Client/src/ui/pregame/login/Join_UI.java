package ui.pregame.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Join_UI extends JFrame {
	JTextField tfId;
	JTextField tfNick;
	JLabel lblLabel1;
	JLabel lblLabel2;
	JLabel lblLabel3;
	JLabel lblLabel4;
	Login_Network net;
	JPasswordField pfPass;
	JPasswordField pfPass2;
	JButton btnJoin_1;

	public Join_UI(Login_Network net) {																	//생성자
		this.net = net;
		init();
		actionListener();

	}


	public void actionListener() {
		btnJoin_1.addActionListener(new ActionListener() {												//회원가입 버튼 액션
			public void actionPerformed(ActionEvent e) {
				if (pfPass.getText().equals(pfPass2.getText())) {										//비밀번호, 비밀번호확인 일치시
					Map resp = net.sendJoin(tfId.getText(), pfPass.getText(),tfNick.getText());
					int r = (int) resp.get("result");
					if (r == 1011) {																	//성공신호 수신시
						JOptionPane.showMessageDialog(Join_UI.this, "회원가입이 완료되었습니다.");
						Join_UI.this.setVisible(false);													//메시지 호출 후 창 끄기
						Join_UI.this.dispose();
					} else {																			//이미 생성된 ID일시
						JOptionPane.showMessageDialog(Join_UI.this, "이미 가입된 Id입니다.");
					}
				} else {
					JOptionPane.showMessageDialog(Join_UI.this, "비밀번호가 동일하지 않습니다.");				//비밀번호, 비밀번호확인 불일치시

				}
				
				
			}
		});
	}

	public void init() {																				//UI 코드
		setSize(400, 400);
		getContentPane().setLayout(null);

		btnJoin_1 = new JButton("\uAC00\uC785\uD558\uAE30");

		btnJoin_1.setBounds(134, 305, 97, 23);
		getContentPane().add(btnJoin_1);

		tfId = new JTextField();
		tfId.setBounds(193, 86, 116, 21);
		getContentPane().add(tfId);
		tfId.setColumns(10);

		tfNick = new JTextField();
		tfNick.setBounds(193, 138, 116, 21);
		getContentPane().add(tfNick);
		tfNick.setColumns(10);

		lblLabel1 = new JLabel("ID");
		lblLabel1.setBounds(95, 89, 57, 15);
		getContentPane().add(lblLabel1);

		lblLabel2 = new JLabel("NickName");
		lblLabel2.setBounds(95, 141, 74, 15);
		getContentPane().add(lblLabel2);

		lblLabel3 = new JLabel("PASSWORD");
		lblLabel3.setBounds(95, 188, 80, 15);
		getContentPane().add(lblLabel3);

		lblLabel4 = new JLabel("PW confirm");
		lblLabel4.setBounds(95, 241, 87, 15);
		getContentPane().add(lblLabel4);

		pfPass = new JPasswordField();
		pfPass.setBounds(193, 185, 116, 21);
		getContentPane().add(pfPass);

		pfPass2 = new JPasswordField();
		pfPass2.setBounds(193, 238, 116, 21);
		getContentPane().add(pfPass2);

	}


}