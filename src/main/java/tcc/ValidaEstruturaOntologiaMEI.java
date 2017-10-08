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


public class ValidaEstruturaOntologiaMEI {

	private OntModel wnOntology;

	public ValidaEstruturaOntologiaMEI() {
		InputStream in = lerArquivoClasspath("ontology_generated/mei_v02");
		OntModel wnOntology = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC.getLanguage());
		wnOntology.read(in, null);
		this.wnOntology = wnOntology;
	}

	public void executarTodasBuscas() throws IOException {
		buscarAtividadesPermitidasPorCnae();
		buscarCnaePorOcupacao();
		buscarCnaePorTextoDaAtividade();
	}

	public void buscarAtividadesPermitidasPorCnae() throws IOException {
		Map<String, String> params = new HashMap<>();
		params.put("cnae", "4785799");
		executarSparql("atividades_permitidas_por_cnae.rf", params);
	}

	public void buscarCnaePorOcupacao() throws IOException {
		Map<String, String> params = new HashMap<>();
		params.put("ocupacaoInformada", "Lojista");
		executarSparql("cnae_por_ocupacao.rf", params);
	}

	public void buscarCnaePorTextoDaAtividade() throws IOException {
		Map<String, String> params = new HashMap<>();
		params.put("descricaoInformada", "comércio");
		executarSparql("cnae_por_atividade.rf", params);
	}

	public void buscarCnaePorServicoEProduto() throws IOException {
		Map<String, String> params = new HashMap<>();
		params.put("servicoInformado", "comércio");
		params.put("produtoInformado", "móvel");
		executarSparql("busca_cnae_por_produto_servico.rf", params);
	}

	private void executarSparql(String sparqlFile, Map<String, String> params) throws IOException {
		String teste = getQuerySparql(sparqlFile);
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

	private String getQuerySparql(String sparqlFileName) throws IOException {
		InputStream in = lerArquivoClasspath(String.format("sparql/oficial/%s", sparqlFileName));
		return new String(IOUtils.toByteArray(in));
	}

	private InputStream lerArquivoClasspath(String uri) {
		ClassLoader classLoader = this.getClass().getClassLoader();
		String url = classLoader.getResource(uri).getFile();
		return FileManager.get().open(url);
	}
}
