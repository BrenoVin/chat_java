package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Cliente extends JFrame implements ActionListener {

	JPanel painel;
	JButton send;
	JTextArea area;
	JLabel label1, label2;
	JTextField input;
	JScrollPane scroll;

	private String nome;
	Socket cliente;
	DataOutputStream out;
	DataInputStream in;
	String mensagem;

	public Cliente(String nome) throws IOException {
		super("Chat - " + nome);
		this.nome = nome;

		try {
			cliente = new Socket("localhost", 2323);
			out = new DataOutputStream(cliente.getOutputStream());
			in = new DataInputStream(cliente.getInputStream());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		painel = new JPanel();
		send = new JButton("Enviar");

		area = new JTextArea(18, 39);
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		area.setEditable(false);
		area.setFocusable(false);

		scroll = new JScrollPane();

		scroll.setViewportView(area);

		input = new JTextField(30);
		send.addActionListener(this);
		painel.add(scroll);
		// painel.add(area);
		painel.add(input);
		painel.add(send);

		this.add(painel);
		this.pack();
		recebeMensagem();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == send) {
			String mensagem = input.getText();
			try {
				out.writeUTF(nome + " : " + mensagem);
			} catch (IOException e1) {
				e1.printStackTrace();
				try {
					cliente.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}

			}
			input.setText("");
			input.requestFocus();
		}
		
	}

	public void recebeMensagem() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						mensagem = in.readUTF();
						area.append(mensagem + "\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}


}
