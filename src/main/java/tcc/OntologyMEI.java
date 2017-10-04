package tcc;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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
//		executeQueryAtividadesPermitidasPorCnae(wnOntology);
//		executeQueryCnaePorOcupacao(wnOntology);
		executeQueryCnaePorAtividade(wnOntology);
	}

	private void executeQueryAtividadesPermitidasPorCnae(OntModel wnOntology) throws IOException {
		Map<String, String> params = new HashMap<>();
		params.put("cnae", "4785799");
		executeQuery(wnOntology, "atividades_permitidas_por_cnae.rf", params);
	}

	private void executeQueryCnaePorOcupacao(OntModel wnOntology) throws IOException {
		Map<String, String> params = new HashMap<>();
		params.put("ocupacaoInformada", "Vendedor");
		executeQuery(wnOntology, "cnae_por_ocupacao.rf", params);
	}
	
	private void executeQueryCnaePorAtividade(OntModel wnOntology) throws IOException {
		Map<String, String> params = new HashMap<>();
		params.put("descricaoInformada", "comércio");
		executeQuery(wnOntology, "cnae_por_atividade.rf", params);
	}

	private void executeQuery(OntModel wnOntology, String sparqlFile, Map<String, String> params) throws IOException {
		String teste = getQueryStringFromSparqlFile(sparqlFile);
		Query query = QueryFactory.create(teste);
		QuerySolutionMap queryMap = new QuerySolutionMap();
		params.entrySet().forEach(pair -> {
			queryMap.add(pair.getKey(), wnOntology.createTypedLiteral(pair.getValue()));
		});

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
