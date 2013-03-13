package br.com.ufrj.msi2.netuno.modelo.enums;

public enum SituacaoContratoEnum {
	
	aguardandoColeta("Aguardando coleta"),
	aberto("Aberto"),
	aguardandoEntrega("Aguardando entrega"),
	finalizado("Finalizado");

	private final String text;

    SituacaoContratoEnum(String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }
}
