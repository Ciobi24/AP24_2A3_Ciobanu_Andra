package lab5;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.xml.catalog.Catalog;
import java.io.File;
import java.io.IOException;

public class RepositoryService {
    public void export(Repository repo, String path)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.
                writeValue(
                        new File(path),
                        repo);
    }

    //??????
//    public Repository read(String path)
//            throws InvalidCatalogException, IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        Repository repository = objectMapper.readValue(
//                new File(path),
//                Repository.class);
//        return repository;
//    }

}
