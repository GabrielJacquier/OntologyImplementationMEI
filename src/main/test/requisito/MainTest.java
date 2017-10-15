package requisito;

import java.io.IOException;

import service.ValidaEstruturaOntologiaMEI;

public class MainTest {

	public static void main(String[] args) throws IOException {
//		ExtratorCnaeTest extratorCnae = new ExtratorCnaeTest();
//		extratorCnae.extract();
		
		ValidaEstruturaOntologiaMEI validator = new ValidaEstruturaOntologiaMEI();
		validator.executarTodasBuscas();
	}

}
