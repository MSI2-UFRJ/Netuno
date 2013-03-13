package br.com.ufrj.msi2.netuno.modelo.enums;

public enum FormaPagamentoEnum {
	
	cartao("Cart�o de Cr�dito"),
	boleto("Boleto Banc�rio");

	private final String text;

    FormaPagamentoEnum(String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }

}
