package model;

import java.time.LocalDate;
import java.util.Objects;

import exceptions.ValorInvalidoExcception;

public class Hospede {

	private Long id;
	private String nome;
	private String sobrenome;
	private LocalDate dataNascimento;
	private String nacionalidade;
	private String telefone;
	private Reserva reserva;


	public Hospede() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Hospede(String nome, String sobrenome, LocalDate dataNascimento, String nacionalidade, String telefone,
			Reserva reserva) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
		this.reserva = reserva;
	}



	public Hospede(Long id, String nome, String sobrenome, LocalDate dataNascimento, String nacionalidade,
			String telefone, Reserva reserva) {
		super();
		validar(nome, sobrenome, dataNascimento, nacionalidade, telefone, id);
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
		this.reserva = reserva;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

	
	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, sobrenome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hospede other = (Hospede) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(sobrenome, other.sobrenome);
	}

	
	
	private void validar(String nome, String sobrenome, LocalDate dataNascimento, String nacionalidade, String telefone,
			Long reservaId) {
		if(nome == null || sobrenome == null || dataNascimento == null 
				|| nacionalidade == null || telefone == null || reservaId == null) {
			throw new ValorInvalidoExcception("valores obrigatorios estao nulos");
		}
	}

}
