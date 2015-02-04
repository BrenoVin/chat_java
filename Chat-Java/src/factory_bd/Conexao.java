package factory_bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao {
	public java.sql.Connection conn = null;
	public static void main(String args[]) throws ClassNotFoundException {
		Conexao n = new Conexao();
		n.conectar();
	}

	public void conectar() throws ClassNotFoundException {
		try {
			final String nome = "postgres";
			final String senha = "root";
			final String url = "jdbc:postgresql://127.0.0.1:5432/chat-java-db";
			Class.forName("org.postgresql.Driver");

			conn = DriverManager.getConnection(url, nome, senha);

		} catch (SQLException e) {
			System.out.println("Erro na conexão");
		}
	}
}
