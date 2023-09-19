package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import exceptions.RepositoryException;
import model.Reserva;
import utils.DateUtils;

public class ReservaRepository {
	
	public Long efetuarReserva(Reserva reserva) {
		
		Long idReserva = null;
		
		try {
			Connection connection = FabricaConexao.abrirConexao();
			String sqlInsert = "INSERT INTO reserva(data_entrada,data_saida,valor,forma_pagamento)"
					          +"VALUES (?,?,?,?);";
			try (PreparedStatement ps = connection.prepareStatement(sqlInsert
					, Statement.RETURN_GENERATED_KEYS)){
				
				ps.setDate(1, (Date) DateUtils.converterData(reserva.getDataEntrada()));
				ps.setDate(2, (Date) DateUtils.converterData(reserva.getDataSaida()));
				ps.setBigDecimal(3, reserva.getValor());
				ps.setString(4, reserva.getFormaPagamento().toString());
				int linhasAfetadas = ps.executeUpdate();
				
				if(linhasAfetadas > 0) {
					try (ResultSet rs = ps.getGeneratedKeys()){
						idReserva = rs.getLong(1);
					} catch (Exception e) {
						System.err.print("erro ao buscar id da reserva");
					}
				}
                 return idReserva;				
			} catch (Exception e) {
				System.err.print("Erro ao executar comando sql");				
			}
			return -1L;
		} catch (Exception e) {
			throw new RepositoryException("Erro ao efetuar reserva");
		}
	}

}
