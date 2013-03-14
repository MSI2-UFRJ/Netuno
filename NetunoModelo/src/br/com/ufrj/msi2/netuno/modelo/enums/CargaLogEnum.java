package br.com.ufrj.msi2.netuno.modelo.enums;

public enum CargaLogEnum {
	
	aguardandoColeta("cargalog.aguardandoColeta"),
	aguardandoClienteEntregar("cargalog.aguarndadoClienteEntregar"),
	cargaRecebidaPortoOrigem("cargalog.cargaRecebidaPortoOrigem"),
	saiuDoPortoOrigem("cargalog.saiuDoPortoOrigem"),
	cargaRecebidaPortoDestino("cargalog.cargaRecebidaPortoDestino"),
	aguardandoEntrega("cargalog.aguardandoEntrega"),
	aguardandoClienteIrPegar("cargalog.aguardandoClienteIrPegar"),
	cargaEntregue("cargalog.cargaEntregue");

	private final String descricao;

    CargaLogEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
		return descricao;
	}

	public String toString() {
        return descricao;
    }

}
