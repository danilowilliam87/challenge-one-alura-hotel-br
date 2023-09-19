package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class UsuarioRepository {
	
	public Usuario buscarPorLogin(String login, String senha) {
		
		Connection connection = FabricaConexao.abrirConexao();
		String sqlBuscarUsuario = "SELECT * FROM usuario "
				                + "WHERE login = ?"
				                + "AND senha  = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sqlBuscarUsuario);
			ps.setString(1, login);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			Usuario usuario = new Usuario();
			while(rs.next()) {
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
			}
			return usuario;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
