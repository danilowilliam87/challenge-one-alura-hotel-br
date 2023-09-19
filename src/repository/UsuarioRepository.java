package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.RepositoryException;
import model.Usuario;

public class UsuarioRepository {
	
	public Usuario buscarPorLogin(String login, String senha) {
		
		Connection connection = FabricaConexao.abrirConexao();
		String sqlBuscarUsuario = "SELECT * FROM usuario "
				                + "WHERE login = ?"
				                + "AND senha  = ?";
		try (PreparedStatement ps = connection.prepareStatement(sqlBuscarUsuario);){
			
			ps.setString(1, login);
			ps.setString(2, senha);
			Usuario usuario = new Usuario();
			try (ResultSet rs = ps.executeQuery();){
				while(rs.next()) {
					usuario.setLogin(rs.getString("login"));
					usuario.setSenha(rs.getString("senha"));
				}
			} catch (Exception e) {
				throw new RepositoryException("Erro ao buscar dados de login - ResultSet");
			}
			return usuario;
		} catch (SQLException e) {
			throw new RepositoryException("Erro ao executar comando sql - PrepareStatement");
		}
	}

}
