package factory_bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class LoginDAO {
	public boolean entrar(Connection con, String nome1, String senha1)
			throws SQLException {

		String sql = "select (nome,senha) from usuarios where nome=? and senha=?";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, nome1);
		pstm.setString(2, senha1);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

}
