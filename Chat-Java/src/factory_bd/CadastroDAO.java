package factory_bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroDAO {

	public void inserir(Connection con, String nome, String email, String senha) {
		try {
			String sql = "insert into usuarios(nome, email, senha) values (?,?,?)";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, nome);
			pstm.setString(2, email);
			pstm.setString(3, senha);

			pstm.executeUpdate();
			// con.commit();

		} catch (Exception e) {
			System.err.println("erro no select");
		}

	}
}
