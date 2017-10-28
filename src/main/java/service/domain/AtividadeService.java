package service.domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.jena.ontology.Individual;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

import service.ontology.MEIResource;
import service.ontology.OntologyService;
import web.response.AtividadeResponse;

@Stateless
public class AtividadeService {

	@Inject
	private OntologyService ontologyService;

	public List<AtividadeResponse> listAllAtividades() {
		List<Individual> individuals = ontologyService.listIndividualsByClassURI(MEIResource.CLASS_ATIVIDADE);

		List<String> atividadesDescricao = individuals.stream()
					.map(individual -> ontologyService.getPropertieValue(individual, MEIResource.PROP_ATIVIDADEDESCRICAO))
					.flatMap(descricoes -> descricoes.stream())
					.collect(Collectors.toList());

		return atividadesDescricao.stream()
				.map(descricao -> new AtividadeResponse(descricao))
				.collect(Collectors.toList());

	}

	public List<AtividadeResponse> listAtividadesByCNAE(String cnae) throws IOException {
		Map<String, String> paramsQuery = new HashMap<>();
		paramsQuery.put("cnae", cnae);

		List<RDFNode> nodes = ontologyService.listRDFNodesBySparql("atividades_permitidas_por_cnae", paramsQuery, "atividade");
		List<Resource> resources = nodes.stream().map(node -> node.asResource()).collect(Collectors.toList());

		List<String> atividadesDescricao = resources.stream()
				.map(resource -> ontologyService.getPropertieValue(resource, MEIResource.PROP_ATIVIDADEDESCRICAO))
				.flatMap(descricoes -> descricoes.stream())
				.collect(Collectors.toList());

		return atividadesDescricao.stream()
			.map(descricao -> new AtividadeResponse(descricao))
			.collect(Collectors.toList());
	}

	public List<AtividadeResponse> listAtividadesByTermos(List<String> termos) throws IOException {
		ontologyService.listRDFNodesByStament("olhar metodo");
		return null;
	}
}
