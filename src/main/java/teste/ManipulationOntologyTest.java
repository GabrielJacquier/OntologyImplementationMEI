package teste;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.util.FileManager;
import org.mindswap.pellet.jena.PelletReasonerFactory;

public class ManipulationOntologyTest {

	public void teste() throws FileNotFoundException {
//		String url_base_owl = "http://www.semanticweb.org/gabriel/ontologies/2017/6/untitled-ontology-3";
//		String NS = url_base_owl + "#";
//
//		ClassLoader classLoader = this.getClass().getClassLoader();
//		String url = classLoader.getResource("familia_rdf.owl").getFile();
//		InputStream in = FileManager.get().open(url);
//
//		OntModel wnOntology = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC.getLanguage());
////		OntModel wnOntology = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF);
//		wnOntology.read(in, null);
//		System.out.println(wnOntology.isEmpty());
//		Property ehIrmaoDe = wnOntology.getProperty(NS + "éTioDe");
//		System.out.println(ehIrmaoDe.getNameSpace());
//		
//		ResIterator irmaos = wnOntology.listResourcesWithProperty(ehIrmaoDe);
//		System.out.println("passou");
//		System.out.println(irmaos.toList().size());
//
//
////		ExtendedIterator<Individual> individuals = wnOntology.listIndividuals(wnOntology.getResource(NS + "Mae"));
//		irmaos.toList().forEach(subject -> {
//			System.out.println(subject.getURI());
//		});

//		OntProperty hasFather = wnOntology.getOntProperty(NS + "hasFather");
//		NodeIterator parent = wnOntology.listObjectsOfProperty(hasFather);
//
//		parent.toList().forEach(subject -> {
//			System.out.println(subject.asResource().getLocalName());
//		});
		
		
		
		
	// PEDAÇOS QUE ESTAVAM NA MAIN
		
		
//		String familyUri = "http://family/";
//		String relationshipUri = "http://purl.org/vocab/relationship/";
//
//		Model model = ModelFactory.createDefaultModel();
//
//		Resource joao = model.createResource(familyUri+"joao");
//		Resource matilde = model.createResource(familyUri+"matilde");
//		Resource henrique = model.createResource(familyUri+"henrique");
//		Resource gabriel = model.createResource(familyUri+"gabriel");
//
//		Property childOf = model.createProperty(relationshipUri,"childOf");
//		Property parentOf = model.createProperty(relationshipUri,"parentOf");
//		Property siblingOf = model.createProperty(relationshipUri,"siblingOf");
//		Property spouseOf = model.createProperty(relationshipUri,"spouseOf");
//
//		joao.addProperty(parentOf, gabriel);
//		joao.addProperty(spouseOf, matilde);
//		joao.addProperty(parentOf, henrique);
//		
//		ResIterator parents = model.listSubjectsWithProperty(spouseOf);
//
//		while (parents.hasNext()) {
//		  Resource person = parents.nextResource();
//		  System.out.println(person.getLocalName());
//		}
		
//		String SOURCE = "http://gabriel.com/technologies/ontology";
//		String NS = SOURCE + "#";
//		
		//OntologyTeste on = new OntologyTeste();
		//on.teste();
//		
//		
//
//		OntModel wnOntology = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF);
		
//		wnOntology.read(null, "www.gabriel.teste");

//		OntClass homem = wnOntology.createClass(NS + "Homem");
//		OntClass mulher = wnOntology.createClass(NS + "Mulher");
//
//		ObjectProperty temEsposa = wnOntology.createObjectProperty( NS + "temEsposa" );
//		temEsposa.addDomain(homem);
//		temEsposa.addRange(mulher);
//
//		ObjectProperty temMarido = wnOntology.createObjectProperty( NS + "temMarido" );
//		temMarido.addDomain(mulher);
//		temMarido.addRange(homem);
//		
//		ObjectProperty ehParente = wnOntology.createObjectProperty( NS + "ehParente" );
//		ehParente.addSubProperty(temEsposa);
//		ehParente.addSubProperty(temMarido);
//
//		temEsposa.addInverseOf(temMarido);
//
//		Individual antonio = wnOntology.createIndividual(NS + "antonio", homem);
//		Individual maria = wnOntology.createIndividual(NS + "maria", mulher);
//
//		antonio.addProperty(temEsposa, maria);
	}
}
