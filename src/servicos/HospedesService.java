package servicos;

import model.Hospede;
import repository.HospedesRepository;
import repository.ReservaRepository;

public class HospedesService {
	
	private HospedesRepository repository = new HospedesRepository();
	private ReservaRepository reservaRepository = new ReservaRepository();
	
	
	public Long salvarHospede(Hospede hospede) {
		return repository.salvarHospede(hospede);
	}
	
	public Hospede buscarPorSobreNome(String sobrenome) {
		return repository.consultarHospedePorSobrenome(sobrenome);
	}
	
	public boolean atualizarHospedes(Hospede hospede) {
		return repository.atualizarHospedes(hospede.getId(), hospede);
	}
	
	public boolean excluirHospedes(Long id) {
		return reservaRepository.excluirReserva(id);
	}
	

}
