package web.response;

import java.util.Optional;

public class AtividadeResponse {

	private String codigoCNAE;
	private String descricao;

	public AtividadeResponse(String descricao) {
		this.descricao = descricao;
	}

	public AtividadeResponse(Optional<String> descricao, Optional<String> cnae) {
		this.descricao = descricao.isPresent() ? descricao.get() : null;
		this.codigoCNAE = cnae.isPresent() ? cnae.get() : null;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigoCNAE() {
		return codigoCNAE;
	}

	public void setCodigoCNAE(String codigoCNAE) {
		this.codigoCNAE = codigoCNAE;
	}

}
