package br.com.ufrj.msi2.netuno.modelo.enums;

public enum FormaPagamentoEnum {
	
	cartao("formaPagamento.cartao"),
	boleto("formaPagamento.boleto");

	private final String text;

    FormaPagamentoEnum(String text) {
        this.text = text;
    }

    public String getText() {
		return text;
	}

	public String toString() {
        return text;
    }

}
