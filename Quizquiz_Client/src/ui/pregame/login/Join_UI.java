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
	ObjectOutputStream oos;
	ObjectInputStream ois;
	JButton btnJoin_1;

	public Join_UI(Login_Network net) {
		this.net = net;
		init();
		actionListener();

	}

//	public Map sendJoin(String id, String pass, String nick) {
//		Map request = new LinkedHashMap<>();
//		User user=new User(id,pass);
//		request.put("user", user);
//		request.put("mode", "join");
////		request.put("nickname", nick);
//
//		try {
//			oos.writeObject(request);
//			return (Map) ois.readObject();
//		} catch (ClassNotFoundException | IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

	public void actionListener() {
		btnJoin_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pfPass.getText().equals(pfPass2.getText())) {
					Map resp = net.sendJoin(tfId.getText(), pfPass.getText(),tfNick.getText());
					int r = (int) resp.get("result");
					if (r == 1011) {
						JOptionPane.showMessageDialog(Join_UI.this, "회원가입이 완료되었습니다.");
						Join_UI.this.setVisible(false);
						Join_UI.this.dispose();
//					} else if (r == 1) {
//						JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
					} else {
						JOptionPane.showMessageDialog(Join_UI.this, "이미 가입된 Id입니다.");
					}
				} else {
					JOptionPane.showMessageDialog(Join_UI.this, "비밀번호가 동일하지 않습니다.");

				}
				
				
			}
		});
	}

	public void init() {
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