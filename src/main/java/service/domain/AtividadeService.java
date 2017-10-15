package service.domain;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.jena.ontology.Individual;

import service.ontology.OntologyService;
import web.response.AtividadeResponse;

@Stateless
public class AtividadeService {

	private static final String CLASS_ATIVIDADE = "Atividade";
	private static final String DESCRICAO_ATIVIDADE = "atividadeDescricao";

	@Inject
	private OntologyService ontologyService;

	public List<AtividadeResponse> listAtividades() {
		List<Individual> individuals = ontologyService.listIndividualsByClassURI(CLASS_ATIVIDADE);
		return individuals.stream()
				.map(individual -> new AtividadeResponse(ontologyService.getPropertieValue(individual, DESCRICAO_ATIVIDADE)))
				.collect(Collectors.toList());
	}
}
