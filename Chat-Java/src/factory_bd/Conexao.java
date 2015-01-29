package factory_bd;

import java.sql.DriverManager;

public class Conexao {
	public java.sql.Connection conexao = null;
	
	public void conectar(){
		try {
			final String nome = "root";
			final String senha = "root";
			final String url = "jdbc:mysql://localhost/chat";
		Class.forName("com.mysql;jdbc.Driver");
		
		conexao = DriverManager.getConnection(url,nome, senha);
		conexao.setAutoCommit(false);
			
		} catch (Exception e) {
			System.out.println("Erro na conexão");
		}
	}
}
