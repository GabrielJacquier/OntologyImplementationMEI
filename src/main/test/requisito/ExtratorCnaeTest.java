package requisito;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.mindswap.pellet.jena.PelletReasonerFactory;


public class ExtratorCnaeTest {

	private String url_base_owl = "http://www.semanticweb.org/gabriel/ontologies/2017/6/untitled-ontology-3";
	private String NS = url_base_owl + "#";
	private OntModel ontologiModelo;
	private List<String> cnaes;
	private Map<Integer, Integer> quantidadePorDivisao;

	public void extract() throws IOException {
		List<String> atividades = extractListFromFile();
		quantidadePorDivisao = new HashMap<>();
		cnaes = atividades.stream().filter(atividade -> Pattern.compile("^[0-9]{1,}").matcher(atividade).find())
				.collect(Collectors.toList());

		cnaes.forEach(cnae -> {
			String divisao = cnae.substring(0, 2);
			Integer divisaoKey = Integer.valueOf(divisao);
			Integer numero = quantidadePorDivisao.get(divisaoKey);
			numero = numero == null ? 0 : numero;
			quantidadePorDivisao.put(divisaoKey, numero + 1);
		});

		showQuantidadePorSecao(1, 3, "A");
		showQuantidadePorSecao(5, 9, "B");
		showQuantidadePorSecao(10, 33, "C");
		showQuantidadePorSecao(35, 35, "D");
		showQuantidadePorSecao(36, 39, "E");
		showQuantidadePorSecao(41, 43, "F");
		showQuantidadePorSecao(45, 47, "G");
		showQuantidadePorSecao(49, 53, "H");
		showQuantidadePorSecao(55, 56, "I");
		showQuantidadePorSecao(58, 63, "J");
		showQuantidadePorSecao(64, 66, "K");
		showQuantidadePorSecao(68, 68, "L");
		showQuantidadePorSecao(69, 75, "M");
		showQuantidadePorSecao(77, 82, "N");
		showQuantidadePorSecao(84, 84, "O");
		showQuantidadePorSecao(85, 85, "P");
		showQuantidadePorSecao(86, 88, "Q");
		showQuantidadePorSecao(90, 93, "R");
		showQuantidadePorSecao(94, 96, "S");
		showQuantidadePorSecao(97, 97, "T");
		showQuantidadePorSecao(99, 99, "U");

		System.out.println("Total: " + quantidadePorDivisao.values().stream().mapToInt(inteiro -> inteiro).sum());
		showQuantidadePorDivisao(49, 53, "N");
		
	}

	private void showQuantidadePorSecao(int menor, int maior, String letter) {
		int acc = 0;
		for (int i = menor; menor <= maior; menor++) {
			Integer value = quantidadePorDivisao.get(menor);
			if(value != null) {
				acc += value;
			}
		}
		System.out.println("Secao: " + letter + " quantidade: " + acc);
	}

	private void showQuantidadePorDivisao(int menor, int maior, String letter) {
		for (int i = menor; menor <= maior; menor++) {
			Integer value = quantidadePorDivisao.get(menor);
			if(value != null && !value.equals(0)) {
				System.out.println(" Divisão: " + menor + " quantidade: " + value);				
			}
		}
	}

	private List<String> extractListFromFile() throws IOException {
		Path caminho = Paths.get("/home/gabriel/work/gabriel/OntologyMEI/src/main/resources/cnaes");
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
