package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
	
	private static final String url = "jdbc:mysql://localhost:3306/hotel_alura_db";
	
	public static Connection abrirConexao() {
		try {
			String usuario = "root";
			String senha = "root";
			return DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("erro ao conectar");
		}
	}

}
