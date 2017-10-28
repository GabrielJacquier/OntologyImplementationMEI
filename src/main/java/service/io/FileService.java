package service.io;

import java.io.IOException;
import java.io.InputStream;

import javax.ejb.Stateless;

import org.apache.commons.io.IOUtils;
import org.apache.jena.util.FileManager;

@Stateless
public class FileService {

	public InputStream readInputStreamFromURI(String uri) {
		ClassLoader classLoader = this.getClass().getClassLoader();
		String url = classLoader.getResource(uri).getFile();
		return FileManager.get().open(url);
	}

	public String readStringFromURI(String path) throws IOException {
		InputStream in = readInputStreamFromURI(path);
		return new String(IOUtils.toByteArray(in));
	}

}
