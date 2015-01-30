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

import factory_bd.CadastroDAO;
import factory_bd.Conexao;

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

		nomeInput = new JTextField(20);
		emailInput = new JTextField(20);
		senhaInput = new JPasswordField(20);
		senhaInput1 = new JPasswordField(20);

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
			if (nomeInput.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o campo nome!");
				nomeInput.requestFocus();
			} else if (emailInput.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o campo email!");
				emailInput.requestFocus();
			} else if (senhaInput.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"Preencha o campo de senha!");
				senhaInput.requestFocus();
			} else if (senhaInput1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Redigite sua senha!");
				senhaInput1.requestFocus();
			} else if (!senhaInput.getText().equals(senhaInput1.getText())) {
				JOptionPane.showMessageDialog(null, "As senhas são diferentes");
				senhaInput.requestFocus();
				senhaInput.setText("");
				senhaInput1.setText("");
			} else {
				// operacoes com o banco de dados
				Conexao connect = new Conexao();
				CadastroDAO cad = new CadastroDAO();
				String name = nomeInput.getText();
				String mail = emailInput.getText();
				String pass = senhaInput.getText();
				System.out.println(name+mail+pass);
				try {
					connect.conectar();
					cad.inserir(connect.conn, name,mail,pass);
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Não foi possivel realizar o cadastro!");
				}
				
				
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
