package model;

public enum FormaPagamento {
	
	DINHEIRO("DINHEIRO"),
	DEBITO("DEBITO"),
	CREDITO("CREDITO");

	private String formaPagamento;

	FormaPagamento(String formaPagamento) {
		// TODO Auto-generated constructor stub
	    this.formaPagamento = formaPagamento;	
	}
	

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	
	
	

}
