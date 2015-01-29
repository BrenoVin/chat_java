package chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {

	JPanel painel;
	JButton entrar, cadastrar;
	JLabel login, senha;
	JPasswordField password;
	JTextField nickname;

	public Login() {
		super("Chat DnaTec 2015");
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
			Cliente c = new Cliente();
			c.setVisible(true);
			c.setDefaultCloseOperation(c.EXIT_ON_CLOSE);
			c.setLocationRelativeTo(null);
			c.setResizable(false);
			c.setSize(450, 350);
			this.dispose();
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
