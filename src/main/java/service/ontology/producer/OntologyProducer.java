package service.ontology.producer;

import java.io.InputStream;

import javax.enterprise.inject.Produces;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.mindswap.pellet.jena.PelletReasonerFactory;

import service.ontology.util.FileUtil;

public class OntologyProducer {

	private static final String PATH_ONTOLOGY = "ontology_generated/mei_v02";

	@Produces
    public OntModel createFileDao() {
		FileUtil fileUtil = new FileUtil();
		InputStream in = fileUtil.readInputFromURI(PATH_ONTOLOGY);
		OntModel ontModel = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC.getLanguage());
		ontModel.read(in, null);
        return ontModel;
    }

}
