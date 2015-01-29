package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Cadastro extends JFrame implements ActionListener {
	JPanel painel;
	JButton voltar, limpar, cadastrar;
	JLabel nome, email, senha, senha1;
	JTextField nomeInput, emailInput;
	JPasswordField senhaInput, senhaInput1;

	public Cadastro() {
		super("Cadastro");

		painel = new JPanel();
		voltar = new JButton("Voltar");
		limpar = new JButton("Limpar");
		cadastrar = new JButton("Cadastrar");
		voltar.addActionListener(this);
		limpar.addActionListener(this);
		cadastrar.addActionListener(this);

		nome = new JLabel("Nome:");
		email = new JLabel("Email:");
		senha = new JLabel("senha:");
		senha1 = new JLabel("senha:");

		nomeInput = new JTextField(18);
		emailInput = new JTextField(18);
		senhaInput = new JPasswordField(18);
		senhaInput1 = new JPasswordField(18);

		painel.add(nome);
		painel.add(nomeInput);
		painel.add(email);
		painel.add(emailInput);
		painel.add(senha);
		painel.add(senhaInput);
		painel.add(senha1);
		painel.add(senhaInput1);

		painel.add(voltar);
		painel.add(limpar);
		painel.add(cadastrar);

		this.add(painel);
		this.pack();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == voltar) {
			Login login = new Login();
			login.setVisible(true);
			login.setDefaultCloseOperation(login.EXIT_ON_CLOSE);
			login.setLocationRelativeTo(null);
			login.setResizable(false);
			login.setSize(220, 110);
			this.dispose();
		} else if (e.getSource() == limpar) {
			limpar();
		} else if (e.getSource() == cadastrar) {
			if (nomeInput.getText().compareTo("") == 0) {
				JOptionPane.showMessageDialog(null, "Preencha o campo nome!");
			} else if (emailInput.getText().compareTo("") == 0) {
				JOptionPane.showMessageDialog(null, "Preencha o campo email!");
			} else if (senhaInput.getText().compareTo("") == 0) {
				JOptionPane.showMessageDialog(null,
						"Preencha o campo de senha!");
			} else if (senhaInput1.getText().compareTo("") == 0) {
				JOptionPane.showMessageDialog(null, "Redigite sua senha!");
			} else if (!senhaInput.getText().equals(senhaInput1.getText())) {
				JOptionPane.showMessageDialog(null, "As senhas são diferentes");
				senhaInput.setText("");
				senhaInput1.setText("");
			} else {
				// operacoes com o banco de dados
				JOptionPane.showMessageDialog(null, "Cadastrado!");
				limpar();
			}
		}
	}

	public void limpar() {
		nomeInput.setText("");
		emailInput.setText("");
		senhaInput.setText("");
		senhaInput1.setText("");
	}
}
