package service.ontology;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;

@Stateless
public class OntologyService {

	private static final String RESOURCE_ONTOLOGY = "http://www.semanticweb.org/gabriel/ontologies/2017/8/mei#";

	@Inject
	private OntModel ontology;

	public List<Individual> listIndividualsByClassURI(String class_uri) {
		OntClass atividadeClass = ontology.getOntClass(RESOURCE_ONTOLOGY + class_uri);
		return ontology.listIndividuals(atividadeClass).toList();
	}
	
	public String getPropertieValue(Individual individual, String propertie) {
		Property propertieOnt = ontology.createProperty(RESOURCE_ONTOLOGY + propertie);
		RDFNode rdfNode = individual.getPropertyValue(propertieOnt);
		return rdfNode.toString();
	}

}
