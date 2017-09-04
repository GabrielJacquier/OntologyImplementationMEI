package tcc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.mindswap.pellet.jena.PelletReasonerFactory;


public class ExtratorOntologia {

	private String url_base_owl = "http://www.semanticweb.org/gabriel/ontologies/2017/6/untitled-ontology-3";
	private String NS = url_base_owl + "#";
	private OntModel ontologiModelo;

	public void extract() throws IOException {
		ontologiModelo = criarOntologia();
		List<String> atividades = extractListFromFile();
		popularOntologia(atividades);
		criarArquivoOntologia();
	}

	private List<String> extractListFromFile() throws IOException {
		Path caminho = Paths.get("/home/gabriel/eclipse-workspace/tcc/src/main/resources/atividades.txt");
		return Files.lines(caminho).collect(Collectors.toList());
	}

	private OntModel criarOntologia() {
		return ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC.getLanguage());
	}

	private void popularOntologia(List<String> atividades) {
		ontologiModelo.createClass(NS + "Mulher");
		ontologiModelo.createObjectProperty( NS + "temEsposa" );
	}

	private List<String> extrairTermos(String atividade) {
		List<String> termosDaAtividade = Arrays.asList(atividade.split("de"));
		return termosDaAtividade;
	}

	private void criarArquivoOntologia() throws IOException {
		System.out.println(ontologiModelo.listClasses().toList());
		System.out.println(ontologiModelo.listAllOntProperties().toList());
		File file = new File("/home/gabriel/Documents/Gabriel/teste.owl");
		file.createNewFile();
		FileOutputStream oFile = new FileOutputStream(file, false);
		ontologiModelo.write(oFile);
	}

}
