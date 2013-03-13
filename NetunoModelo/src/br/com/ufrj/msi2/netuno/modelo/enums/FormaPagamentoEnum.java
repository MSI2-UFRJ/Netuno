package br.com.ufrj.msi2.netuno.modelo.enums;

public enum FormaPagamentoEnum {
	
	cartao("Cartão de Crédito"),
	boleto("Boleto Bancário");

	private final String text;

    FormaPagamentoEnum(String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }

}
