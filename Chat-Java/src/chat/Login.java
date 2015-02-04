package chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import factory_bd.Conexao;
import factory_bd.LoginDAO;

public class Login extends JFrame implements ActionListener {

	JPanel painel;
	JButton entrar, cadastrar;
	JLabel login, senha;
	JPasswordField password;
	JTextField nickname;

	Socket cliente;

	public Login() {
		super("Chat DnaTec");
		painel = new JPanel();

		entrar = new JButton("Entrar");
		cadastrar = new JButton("Cadastar");
		entrar.addActionListener(this);
		cadastrar.addActionListener(this);

		login = new JLabel("Nome: ");
		senha = new JLabel("Senha:");

		nickname = new JTextField(12);
		password = new JPasswordField(12);

		painel.add(login);
		painel.add(login);
		painel.add(nickname);
		painel.add(senha);
		painel.add(password);
		painel.add(cadastrar);
		painel.add(entrar);

		this.add(painel);
		this.pack();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == entrar) {
			// validar com banco de dados

			Conexao con = new Conexao();
			LoginDAO ld = new LoginDAO();

			String nome1 = nickname.getText();
			String senha1 = password.getText();
			boolean a = false;
			try {
				con.conectar();
				a = ld.entrar(con.conn, nome1, senha1);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}

			if (a) {

				Cliente c = null;
				try {
					c = new Cliente(nome1);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				c.setVisible(true);
				c.setLocationRelativeTo(null);
				c.setResizable(false);
				c.setDefaultCloseOperation(c.EXIT_ON_CLOSE);
				c.setSize(450, 360);
				this.dispose();

			} else {
				JOptionPane.showMessageDialog(null, "Login invalido");
				nickname.setText("");
				password.setText("");
			}

		} else if (e.getSource() == cadastrar) {
			// direcionar para tela de cadastro
			Cadastro cad = new Cadastro();
			cad.setVisible(true);
			cad.setDefaultCloseOperation(cad.EXIT_ON_CLOSE);
			cad.setLocationRelativeTo(null);
			cad.setResizable(false);
			cad.setSize(300, 170);// largura, altura
			this.dispose();
		}

	}

}
