package web.response;

import java.util.List;

public class CNAEResponse {

	private String codigo;
	private List<String> atividades;

	public CNAEResponse(String codigo, List<String> atividades) {
		this.codigo = codigo;
		this.atividades = atividades;
	}

	public CNAEResponse(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<String> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<String> atividades) {
		this.atividades = atividades;
	}

}
