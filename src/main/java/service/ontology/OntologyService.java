package service.ontology;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.jena.ext.com.google.common.base.Predicate;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.QuerySolutionMap;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import service.io.FileService;

@Stateless
public class OntologyService {

	@Inject
	private OntModel ontology;
	
	@Inject
	private FileService fileUtil;

	public List<Individual> listIndividualsByClassURI(String classURI) {
		OntClass atividadeClass = ontology.getOntClass(classURI);
		return ontology.listIndividuals(atividadeClass).toList();
	}

	public List<String> getPropertieValue(Resource resource, String propertieURI) {
		Property propertieOnt = ontology.createProperty(propertieURI);
		NodeIterator iterator = ontology.listObjectsOfProperty(resource, propertieOnt);
		return iterator.toList().stream().map(node -> node.toString()).collect(Collectors.toList());
	}

	public List<RDFNode> listRDFNodesBySparql(String sparqlFile, Map<String, String> params, String paramNameRDFNode) throws IOException {
		List<QuerySolution> solutions = executeSparql(sparqlFile, params);
		return solutions.stream().map(solution -> solution.get(paramNameRDFNode)).collect(Collectors.toList());
	}
	
	public void listRDFNodesByStament(String sparqlFile) throws IOException {
		Property propertieOnt = ontology.createProperty(MEIResource.PROP_ATIVIDADEDESCRICAO);
		Statement stament = ontology.createStatement(null, propertieOnt, "oo");
		stament.getSubject();
		StmtIterator smtIter = ontology.listStatements();
	}

	private List<QuerySolution> executeSparql(String sparqlFile, Map<String, String> params) throws IOException {
		String teste = fileUtil.readStringFromURI(String.format("sparql/oficial/%s.rf", sparqlFile));
		Query query = QueryFactory.create(teste);
		QuerySolutionMap queryMap = new QuerySolutionMap();
		params.entrySet().forEach(pair -> {
			queryMap.add(pair.getKey(), ontology.createTypedLiteral(pair.getValue()));
		});

		QueryExecution qe = QueryExecutionFactory.create(query, ontology, queryMap);
		ResultSet results = qe.execSelect();
		List<QuerySolution> solutions = new ArrayList<>();
		while (results.hasNext()) {
			solutions.add(results.next());
		}
		qe.close();
		return solutions;
	}

}
