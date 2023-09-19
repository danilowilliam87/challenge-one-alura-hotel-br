package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Reserva {
	
	private Long id;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;
	private BigDecimal valor;
	private FormaPagamento formaPagamento;
	
	public Reserva() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reserva(Long id, LocalDate dataEntrada, LocalDate dataSaida, BigDecimal valor,
			FormaPagamento formaPagamento) {
		super();
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public BigDecimal getValor() {
		return valor;
	}

	

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

}
