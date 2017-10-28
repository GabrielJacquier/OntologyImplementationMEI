package service.ontology.producer;

import java.io.InputStream;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.mindswap.pellet.jena.PelletReasonerFactory;

import service.io.FileService;

public class OntologyProducer {

	private static final String PATH_ONTOLOGY = "ontology_generated/mei_v02";

	@Inject
	private FileService fileUtil;

	@Produces
    public OntModel createFileDao() {
		InputStream in = fileUtil.readInputStreamFromURI(PATH_ONTOLOGY);
		OntModel ontModel = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC.getLanguage());
		ontModel.read(in, null);
        return ontModel;
    }

}
