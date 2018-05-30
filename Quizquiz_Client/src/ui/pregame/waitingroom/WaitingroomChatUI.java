package ui.pregame.waitingroom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import data.network.ClientNetworkInfo;
import pregame.waitingroom.WaitingroomChatNetwork;

//import ui.pregame.login.Login_Network;

import javax.swing.JScrollPane;

public class WaitingroomChatUI extends JPanel {
	
	JTextField tfChat;
	WaitingroomChatNetwork net;
	public JTextArea taChatLog;
	JButton btnSend;
	ClientNetworkInfo clientNetwork;
	
	public WaitingroomChatUI(ClientNetworkInfo net) {
		clientNetwork = net;
		init();
		addEventHandler(); 
	

	}
	
	public void addEventHandler() {
		tfChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					net = new WaitingroomChatNetwork(clientNetwork);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String txt =tfChat.getText();
				if(txt.trim().length()!=0) {
					net.sendChat(txt);
				}
				tfChat.setText("");
			}
		});
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txt =tfChat.getText();
				if(txt.trim().length()!=0) {
					net.sendChat(txt);
				}
				tfChat.setText("");
			}
		});
	}

	public void init() {
		setSize(600,200);
		setLayout(null);
		
		tfChat = new JTextField();
		tfChat.setBounds(12, 169, 457, 21);
		add(tfChat);
		tfChat.setColumns(10);
		
		btnSend = new JButton("\uBCF4\uB0B4\uAE30");
		btnSend.setBounds(481, 168, 97, 23);
		add(btnSend);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 563, 150);
		add(scrollPane);
		
		taChatLog = new JTextArea();
		taChatLog.setEditable(false);
		scrollPane.setViewportView(taChatLog);
	
	}
//	public static void main(String[] args) {
//		JFrame f = new JFrame();
//		f.setSize(1000, 1000);
//		
//		WaitingroomChatUI ui = new WaitingroomChatUI();;
//		f.add(ui);
//		
//		f.setVisible(true);
//	}
}
 