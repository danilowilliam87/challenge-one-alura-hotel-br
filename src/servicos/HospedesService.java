package servicos;

import model.Hospede;
import repository.HospedesRepository;

public class HospedesService {
	
	private HospedesRepository repository = new HospedesRepository();
	
	
	public Long salvarHospede(Hospede hospede) {
		return repository.salvarHospede(hospede);
	}
	
	public Hospede buscarPorSobreNome(String sobrenome) {
		return repository.consultarHospedePorSobrenome(sobrenome);
	}
	
	public boolean atualizarHospedes(Hospede hospede) {
		return repository.atualizarHospedes(hospede.getId(), hospede);
	}
	

}
