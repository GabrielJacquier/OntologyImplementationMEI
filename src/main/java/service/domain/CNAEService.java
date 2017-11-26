package service.domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.jena.rdf.model.RDFNode;

import service.ontology.OntologyService;
import web.response.CNAEResponse;

@Stateless
public class CNAEService {

	@Inject
	private OntologyService ontologyService;
	
	@Inject
	private AtividadeService atividadeService;

	public List<CNAEResponse> listCNAEsPorOcupacao(String ocupacao) throws IOException {
		Map<String, String> paramsQuery = new HashMap<>();
		paramsQuery.put("ocupacaoInformada", ocupacao);

		List<RDFNode> cnaes = ontologyService.listRDFNodesBySparql("cnae_por_ocupacao", paramsQuery, "cnae");
		return cnaes.stream().map(cnae -> {
			List<String> atividadesDescricao = atividadeService.listDescricaoAtividadeByCNAE(cnae.toString());
			return new CNAEResponse(cnae.toString(), atividadesDescricao);
		}).collect(Collectors.toList());
	}

}
