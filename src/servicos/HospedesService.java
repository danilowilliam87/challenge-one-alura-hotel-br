package servicos;

import model.Hospede;
import repository.HospedesRepository;

public class HospedesService {
	
	private HospedesRepository repository = new HospedesRepository();
	
	
	public Long salvarHospede(Hospede hospede) {
		return repository.efetuarReserva(hospede);
	}
	
	

}
