package repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exceptions.RepositoryException;
import model.FormaPagamento;
import model.Reserva;
import utils.DateUtils;

public class ReservaRepository {

	public Long efetuarReserva(Reserva reserva) {

		Long idReserva = null;

		try {
			Connection connection = FabricaConexao.abrirConexao();
			String sqlInsert = "INSERT INTO reservas(data_entrada,data_saida,valor,forma_pagamento)"
					+ "VALUES (?,?,?,?);";
			try (PreparedStatement ps = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {

				ps.setDate(1, (Date) DateUtils.converterData(reserva.getDataEntrada()));
				ps.setDate(2, (Date) DateUtils.converterData(reserva.getDataSaida()));
				ps.setBigDecimal(3, reserva.getValor());
				ps.setString(4, reserva.getFormaPagamento().toString());
				int linhasAfetadas = ps.executeUpdate();

				if (linhasAfetadas > 0) {
					try (ResultSet rs = ps.getGeneratedKeys()) {
						rs.next();
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

	public boolean altualizarReserva(Long id, Reserva reserva) {

		int linhasAfetadas = 0;
		try {
			Connection connection = FabricaConexao.abrirConexao();
			String sqlUpdate = "UPDATE  reservas SET data_entrada = ?" + "data_saida = ?" + "valor = ?"
					+ "forma_pagamento" + "	WHERE id = ?";
			try (PreparedStatement ps = connection.prepareStatement(sqlUpdate)) {

				ps.setDate(1, (Date) DateUtils.converterData(reserva.getDataEntrada()));
				ps.setDate(2, (Date) DateUtils.converterData(reserva.getDataSaida()));
				ps.setBigDecimal(3, reserva.getValor());
				ps.setString(4, reserva.getFormaPagamento().toString());
				ps.setLong(5, id);
				linhasAfetadas = ps.executeUpdate();

			} catch (Exception e) {
				System.err.print("Erro ao executar comando sql");
			}
			return linhasAfetadas > 0;
		} catch (Exception e) {
			throw new RepositoryException("Erro ao efetuar reserva");
		}
	}

	public boolean excluirReserva(Long id) {
		int linhasAfetadas = 0;
		try {
			Connection connection = FabricaConexao.abrirConexao();
			String sqlUpdate = "DELETE FROM reservas WHERE id = ?";
			try (PreparedStatement ps = connection.prepareStatement(sqlUpdate)) {

				ps.setLong(1, id);
				linhasAfetadas = ps.executeUpdate();

			} catch (Exception e) {
				System.err.print("Erro ao executar comando sql");
			}
			return linhasAfetadas > 0;
		} catch (Exception e) {
			throw new RepositoryException("Erro ao efetuar reserva");
		}
	}

	public List<Reserva> listarTodasReservas() {
		List<Reserva> reservas = new ArrayList<Reserva>();
		Connection connection = FabricaConexao.abrirConexao();
		String sqlSelect = "SELECT * FROM reservas";
		try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					Long id = rs.getLong("id");
					LocalDate dataEntrada = DateUtils.converterData(rs.getDate("data_entrada"));
					LocalDate dataSaida = DateUtils.converterData(rs.getDate("data_saida"));
					BigDecimal valor = rs.getBigDecimal("valor");
					Reserva reserva = new Reserva(id, dataEntrada, dataSaida, valor, FormaPagamento.valueOf(rs.getString("forma_pagamento")));
					
					reservas.add(reserva);
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.err.print("erro ao adicionar dados na lista");
			}

		} catch (Exception e) {
			System.err.print("Erro ao executar comando sql");
		}
		return reservas;
	}
	
	public Reserva consultarReserva(Long id) {
		Connection connection = FabricaConexao.abrirConexao();
		String sqlSelect = "SELECT * FROM reservas WHERE id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					LocalDate dataEntrada = DateUtils.converterData(rs.getDate("data_entrada"));
					LocalDate dataSaida = DateUtils.converterData(rs.getDate("data_saida"));
					BigDecimal valor = rs.getBigDecimal("valor");
					Reserva reserva = new Reserva(id, dataEntrada, dataSaida, valor, FormaPagamento.valueOf(rs.getString("forma_pagamento")));
					return reserva;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.err.print("erro ao adicionar dados no objeto ");
			}

		} catch (Exception e) {
			System.err.print("Erro ao executar comando sql");
		}
		return null;
	}
	

}
