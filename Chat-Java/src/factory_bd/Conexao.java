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
/*			String sql = "insert into usuarios(nome, email, senha) values (?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "admin");
			ps.setString(2, "admin@gmail.com");
			ps.setString(3, "12345");

//			ps.executeQuery();
			
			int num = ps.executeUpdate();
			System.out.println(num);
*/
		} catch (SQLException e) {
			System.out.println("Erro na conexão");
		}
	}
}
