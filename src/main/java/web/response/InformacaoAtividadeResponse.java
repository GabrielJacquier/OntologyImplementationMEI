package web.response;

import java.util.ArrayList;
import java.util.List;

public class InformacaoAtividadeResponse {

	public InformacaoAtividadeResponse() {
		this.termosDaPesquisaEncontrados = new ArrayList<>();
		this.atividadesEncontradas = new ArrayList<>();
	}

	public InformacaoAtividadeResponse(List<String> termosDaPesquisaEncontrados,
			List<AtividadeResponse> atividadesEncontradas) {
		this.termosDaPesquisaEncontrados = termosDaPesquisaEncontrados;
		this.atividadesEncontradas = atividadesEncontradas;
	}



	private List<String> termosDaPesquisaEncontrados;
	private List<AtividadeResponse> atividadesEncontradas;

	public List<AtividadeResponse> getAtividadesEncontradas() {
		return atividadesEncontradas;
	}

	public void setAtividadesEncontradas(List<AtividadeResponse> atividadesEncontradas) {
		this.atividadesEncontradas = atividadesEncontradas;
	}

	public List<String> getTermosDaPesquisaEncontrados() {
		return termosDaPesquisaEncontrados;
	}

	public void setTermosDaPesquisaEncontrados(List<String> termosDaPesquisaEncontrados) {
		this.termosDaPesquisaEncontrados = termosDaPesquisaEncontrados;
	}

}
