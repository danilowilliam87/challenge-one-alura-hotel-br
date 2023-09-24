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

import com.toedter.calendar.DateUtil;

import exceptions.RepositoryException;
import model.FormaPagamento;
import model.Hospede;
import model.Reserva;
import utils.DateUtils;

public class HospedesRepository {

	public Long efetuarReserva(Hospede hospede) {

		Long idReserva = null;

		try {
			Connection connection = FabricaConexao.abrirConexao();
			String sqlInsert = "INSERT INTO hospedes"
					+ "(nome,sobrenome,data_nascimento,nacionalidade,telefone,reserva_id)"
					+ "VALUES (?,?,?,?,?,?);";
			try (PreparedStatement ps = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {

				ps.setString(1, hospede.getNome());
				ps.setString(2, hospede.getSobrenome());
				ps.setDate(3, DateUtils.converterData(hospede.getDataNascimento()));
				ps.setString(4, hospede.getNacionalidade());
				ps.setString(5, hospede.getTelefone());
				ps.setLong(6, hospede.getReserva().getId());
				
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

	public boolean atualizarHospedes(Long id, Hospede hospede) {

		int linhasAfetadas = 0;
		try {
			Connection connection = FabricaConexao.abrirConexao();
			String sqlUpdate = "UPDATE hospedes SET "
					+ "nome = ? " 
			        + "sobrenome = ? " 
					+ "data_nascimento = ? "
					+ "nacionalidade = ? "
					+ "telefone = ? "
					+ "reserva_id = ? " 
					+ "	WHERE id = ? ";
			try (PreparedStatement ps = connection.prepareStatement(sqlUpdate)) {

				ps.setString(1, hospede.getNome());
				ps.setString(2, hospede.getSobrenome());
				ps.setDate(3, DateUtils.converterData(hospede.getDataNascimento()));
				ps.setString(4, hospede.getNacionalidade());
				ps.setString(5, hospede.getTelefone());
				ps.setLong(6, hospede.getReserva().getId());
				linhasAfetadas = ps.executeUpdate();

			} catch (Exception e) {
				System.err.print("Erro ao executar comando sql");
			}
			return linhasAfetadas > 0;
		} catch (Exception e) {
			throw new RepositoryException("Erro ao atualizar hospedes");
		}
	}

	public boolean excluirReserva(Long id) {
		int linhasAfetadas = 0;
		try {
			Connection connection = FabricaConexao.abrirConexao();
			String sqlUpdate = "DELETE FROM hospedes WHERE id = ?";
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

	public List<Hospede> listarHospedes() {
		List<Hospede> hospedes = new ArrayList<Hospede>();
		Connection connection = FabricaConexao.abrirConexao();
		String sqlSelect = "SELECT h.* FROM hospedes h inner join reservas r on (h.reserva_id = r.id)";
		try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					Long id = rs.getLong("h.id");
					String nome = rs.getString("h.nome");
					String sobrenome = rs.getString("h.sobrenome");
					LocalDate dataNascimento = DateUtils.converterData(rs.getDate("h.data_nascimento"));
					String nacionalidade = rs.getString("h.nacionalidade");
					String telefone = rs.getString("h.telefone");
					Long idReserva = rs.getLong("h.reserva_id");
					LocalDate dataEntrada = DateUtils.converterData(rs.getDate("r.data_entrada"));
					LocalDate dataSaida = DateUtils.converterData(rs.getDate("r.data_saida"));
					BigDecimal valorReserva = rs.getBigDecimal("r.valor");
					String formaPagamento = rs.getString("r.forma_pagamento");
					Reserva reserva = new Reserva(dataEntrada, dataSaida, valorReserva, FormaPagamento.valueOf(formaPagamento));
					reserva.setId(idReserva);
					Hospede hospedes2 = new Hospede(id, nome, sobrenome, dataNascimento, nacionalidade, telefone, reserva);
					hospedes.add(hospedes2);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.err.print("erro ao adicionar dados na lista");
			}

		} catch (Exception e) {
			System.err.print("Erro ao executar comando sql");
		}
		return hospedes;
	}
	
	public Hospede consultarReserva(Long id) {
		Connection connection = FabricaConexao.abrirConexao();
		String sqlSelect = "SELECT h.* FROM hospedes h inner join reservas r on (h.reserva_id = r.id) WHERE ID = ?";
		try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()){
				Hospede hospedes2 = new Hospede();
				while(rs.next()) {
					String nome = rs.getString("h.nome");
					String sobrenome = rs.getString("h.sobrenome");
					LocalDate dataNascimento = DateUtils.converterData(rs.getDate("h.data_nascimento"));
					String nacionalidade = rs.getString("h.nacionalidade");
					String telefone = rs.getString("h.telefone");
					Long idReserva = rs.getLong("h.reserva_id");
					LocalDate dataEntrada = DateUtils.converterData(rs.getDate("r.data_entrada"));
					LocalDate dataSaida = DateUtils.converterData(rs.getDate("r.data_saida"));
					BigDecimal valorReserva = rs.getBigDecimal("r.valor");
					String formaPagamento = rs.getString("r.forma_pagamento");
					Reserva reserva = new Reserva(dataEntrada, dataSaida, valorReserva, FormaPagamento.valueOf(formaPagamento));
					reserva.setId(idReserva);
					hospedes2 = new Hospede(id, nome, sobrenome, dataNascimento, nacionalidade, telefone, reserva);
				}
				return hospedes2;
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
