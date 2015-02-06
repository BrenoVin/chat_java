package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	Thread t;

	private String nome;
	Socket cliente;
	DataOutputStream out;
	DataInputStream in;
	String mensagem;

	public Cliente(String nome){
		super("Chat - " + nome);
		this.nome = nome;

		try {
			cliente = new Socket("localhost", 2323);
			out = new DataOutputStream(cliente.getOutputStream());
			in = new DataInputStream(cliente.getInputStream());

		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Servidor Indisponível!");
		} catch (IOException e) {
			System.err.println("Erro na comunicação!");;
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
		
		this.addWindowListener(new WindowAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void windowClosing(WindowEvent evt){
				t.stop();
				try {
					cliente.close();
				} catch (IOException e) {
					System.err.println("erro ao fechar o cliente");;
				}
				dispose();
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == send) {
			String mensagem = input.getText();
			try {
				out.writeUTF(nome + " : " + mensagem);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Mensagem não enviada!");
			}
			input.setText("");
			input.requestFocus();
		}

	}

	public void recebeMensagem() {
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						mensagem = in.readUTF();
						area.append(mensagem + "\n");
					} catch (IOException e) {
						System.err.println("Erro ao receber as mensagens!");
					}
				}
			}
		});
		t.start();
	}

}
