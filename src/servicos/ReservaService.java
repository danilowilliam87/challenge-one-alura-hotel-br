package servicos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import model.Reserva;
import repository.ReservaRepository;

public class ReservaService {
	
	private ReservaRepository repository = new ReservaRepository();
	private BigDecimal valorDiaria = new BigDecimal(100);
	
	public Long criarReserva(Reserva reserva) {
		reserva.setValor(calcularTotalReserva(reserva.getDataEntrada(), reserva.getDataSaida()));
		return this.repository.efetuarReserva(reserva);
	}
	
	public Reserva consultarReserva(Long id) {
		return this.repository.consultarReserva(id);
	}
	
	public boolean excluirReserva(Long id) {
		return this.repository.excluirReserva(id);
	}
	
	public boolean atualizarReserva(Long id, Reserva reserva) {
		return this.repository.altualizarReserva(id, reserva);
	}
	
	public List<Reserva> listarReservas(){
		return this.repository.listarTodasReservas();
	}
	
	public BigDecimal calcularTotalReserva(LocalDate entrada, LocalDate saida) {
		 long diferencaEmDias = ChronoUnit.DAYS.between(entrada, saida);
		 return valorDiaria.multiply(new BigDecimal(diferencaEmDias));
	}

}
