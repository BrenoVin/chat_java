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
		super("Chat DnaTec");
		painel = new JPanel(new GridLayout(3, 1));

		entrar = new JButton("Entrar");
		cadastrar = new JButton("Cadastar");
		entrar.addActionListener(this);
		cadastrar.addActionListener(this);

		login = new JLabel("Nome");
		senha = new JLabel("Senha");

		nickname = new JTextField(20);
		password = new JPasswordField(20);

		painel.add(login);
		painel.add(login);
		painel.add(nickname);
		painel.add(senha);
		painel.add(password);
		painel.add(cadastrar);
		painel.add(entrar);

		this.add(painel);

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
			c.setSize(450, 390);
			this.dispose();
		} else if (e.getSource() == cadastrar) {
			// direcionar para tela de cadastro
		}

	}

}
