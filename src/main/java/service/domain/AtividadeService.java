package service.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

		return individuals.stream()
				.map(resource -> {
					Optional<String> subclasseCodigo = ontologyService.getPropertieValueOfResourceOfPropertie(resource,
							MEIResource.PROP_PERMITIDAPOR, MEIResource.PROP_CONJUNTOCODIGO).stream().findAny();

					Optional<String> descricaoAtividade = ontologyService
							.getPropertieValue(resource, MEIResource.PROP_ATIVIDADEDESCRICAO).stream().findAny();

					return new AtividadeResponse(descricaoAtividade, subclasseCodigo);
				})
				.sorted((p1, p2) -> p1.getCodigoCNAE().compareTo(p2.getCodigoCNAE()))
				.collect(Collectors.toList());
	}

	public List<AtividadeResponse> listAtividadesByCNAE(String cnae) throws IOException {
		List<Resource> resources = listResourceAtividadeByCNAE(cnae);

		return resources.stream()
				.map(resource -> {
					Optional<String> subclasseCodigo = ontologyService.getPropertieValueOfResourceOfPropertie(resource, MEIResource.PROP_PERMITIDAPOR, MEIResource.PROP_CONJUNTOCODIGO).stream().findAny();
					Optional<String> descricaoAtividade = ontologyService.getPropertieValue(resource, MEIResource.PROP_ATIVIDADEDESCRICAO).stream().findAny();
					return new AtividadeResponse(descricaoAtividade, subclasseCodigo);
				})
				.collect(Collectors.toList());
	}

	public List<String> listDescricaoAtividadeByCNAE(String cnae) {
		try {
			List<Resource> resources = listResourceAtividadeByCNAE(cnae);
			return resources.stream()
					.map(resource -> ontologyService.getPropertieValue(resource, MEIResource.PROP_ATIVIDADEDESCRICAO).stream().findAny().orElse(""))
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	private List<Resource> listResourceAtividadeByCNAE(String cnae) throws IOException {
		Map<String, String> paramsQuery = new HashMap<>();
		paramsQuery.put("cnae", cnae);
		List<RDFNode> nodes = ontologyService.listRDFNodesBySparql("atividades_permitidas_por_cnae", paramsQuery, "atividade");
		return nodes.stream().map(node -> node.asResource()).collect(Collectors.toList());
	}

	public List<AtividadeResponse> listAtividadesByTermos(List<String> termos) throws IOException {
		List<RDFNode> nodes = ontologyService.listRDFNodesByPropertieValue(MEIResource.PROP_LABEL, termos);

		List<Individual> individuals = ontologyService.listindividualsByClass(MEIResource.CLASS_ATIVIDADE);
		List<Resource> resources = individuals.stream().map(i -> i.asResource()).collect(Collectors.toList());
		
		resources = ontologyService.filterResourceWithAllPropertieParams(MEIResource.PROP_TEMELEMENTO, resources, nodes);
		resources = ontologyService.filterResourceNotHasExcecaoToParams(resources, nodes);
		
		return resources.stream()
				.map(resource -> {
					Optional<String> subclasseCodigo = ontologyService.getPropertieValueOfResourceOfPropertie(resource, MEIResource.PROP_PERMITIDAPOR, MEIResource.PROP_CONJUNTOCODIGO).stream().findAny();
					Optional<String> descricaoAtividade = ontologyService.getPropertieValue(resource, MEIResource.PROP_ATIVIDADEDESCRICAO).stream().findAny();
					return new AtividadeResponse(descricaoAtividade, subclasseCodigo);
				})
				.collect(Collectors.toList());
	}
}
