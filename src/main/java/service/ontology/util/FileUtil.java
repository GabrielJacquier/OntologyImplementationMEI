package service.ontology.util;

import java.io.InputStream;

import javax.ejb.Stateless;

import org.apache.jena.util.FileManager;

@Stateless
public class FileUtil {

	public InputStream readInputFromURI(String uri) {
		ClassLoader classLoader = this.getClass().getClassLoader();
		String url = classLoader.getResource(uri).getFile();
		return FileManager.get().open(url);
	}

}
