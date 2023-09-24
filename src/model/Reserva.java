package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import exceptions.ValorInvalidoExcception;

public class Reserva {
	
	private Long id;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;
	private BigDecimal valor;
	private FormaPagamento formaPagamento;
	
	

	public Reserva(LocalDate dataEntrada, LocalDate dataSaida, BigDecimal valor, FormaPagamento formaPagamento) {
		super();
		validarConstrutor(dataEntrada, dataSaida, valor);
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
	}
	
	public Reserva(LocalDate dataEntrada, LocalDate dataSaida, BigDecimal valor) {
		validarConstrutor(dataEntrada, dataSaida, valor);
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
	}



	public Reserva(Long id, LocalDate dataEntrada, LocalDate dataSaida, BigDecimal valor,
			FormaPagamento formaPagamento) {
		validarConstrutor(dataEntrada, dataSaida, valor);
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

//	public void setDataEntrada(LocalDate dataEntrada) {
//		if(this.dataEntrada.isAfter(dataSaida)) {
//			throw new ValorInvalidoExcception("a data de entrarda tem que ser menor que a de saida");
//
//		}
//		this.dataEntrada = dataEntrada;
//	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

//	public void setDataSaida(LocalDate dataSaida) {
//		if(this.dataSaida.isBefore(dataEntrada)) {
//			throw new ValorInvalidoExcception("a data de saida tem que ser maior que a de entrada");
//		}
//		this.dataSaida = dataSaida;
//	}

	public BigDecimal getValor() {
		return valor;
	}

	

	public void setValor(BigDecimal valor) {
		if(valor == null || valor.doubleValue() < new BigDecimal(100).doubleValue()) {
			throw new ValorInvalidoExcception("valor nao permitido");
		}
		this.valor = valor;
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
	
	
	private void validarConstrutor(LocalDate dataEntrada, LocalDate dataSaida, BigDecimal valor) {
		if(dataEntrada.isAfter(dataSaida) && dataEntrada.isBefore(LocalDate.now())) {
			throw new ValorInvalidoExcception("a data de entrarda tem que ser menor que a de saida");
		}
		
		if(dataSaida.isBefore(dataEntrada)) {
			throw new ValorInvalidoExcception("a data de saida tem que ser maior que a de entrada");
		}
		
		if(valor == null || valor.doubleValue() < new BigDecimal(100).doubleValue()) {
			throw new ValorInvalidoExcception("valor nao permitido");
		}
		
		
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida + ", valor=" + valor
				+ ", formaPagamento=" + formaPagamento + "]";
	}
	
	
   
	
}
