package tcc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolutionMap;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.mindswap.pellet.jena.PelletReasonerFactory;


public class OntologyMEI {

	public void reasoner() throws IOException {
		InputStream in = readFileFromResource("ontology_infered/mei_v01");
		OntModel wnOntology = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC.getLanguage());
		wnOntology.read(in, null);
		executeQueryTeste(wnOntology);
	}

	private void executeQueryTeste(OntModel wnOntology) throws IOException {
		String teste = getQueryStringFromSparqlFile("sparql_cnaes_v1.rf");
		Query query = QueryFactory.create(teste);
		QuerySolutionMap queryMap = new QuerySolutionMap();
		queryMap.add("codigo", wnOntology.createTypedLiteral("141"));
		QueryExecution qe = QueryExecutionFactory.create(query, wnOntology, queryMap);
		ResultSet results = qe.execSelect();
		ResultSetFormatter.out(System.out, results, query);
		qe.close();
	}

	private String getQueryStringFromSparqlFile(String sparqlFileName) throws IOException {
		InputStream in = readFileFromResource(String.format("sparql/%s", sparqlFileName));
		return new String(IOUtils.toByteArray(in));
	}

	private InputStream readFileFromResource(String uri) {
		ClassLoader classLoader = this.getClass().getClassLoader();
		String url = classLoader.getResource(uri).getFile();
		return FileManager.get().open(url);
	}
}
