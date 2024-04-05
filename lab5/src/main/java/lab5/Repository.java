package lab5;

import lombok.Getter;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Stream;


@Getter
public class Repository {
    private String directory;
    private Map<Person, List<Document>> documents = new HashMap<>();

    public Repository(String directory) throws InvalidRepositoryException {
        this.directory = directory;
        Path dirPath = Paths.get(directory);
        if (!Files.exists(dirPath) || !Files.isDirectory(dirPath)) {
            throw new InvalidRepositoryException(new Exception(directory + " is not a valid directory"));
        }
        loadDocuments();
    }

    private void loadDocuments() {
        this.documents.clear();
        List<Path> subDirectories;
        try {
            Stream<Path> walk = Files.list(Paths.get(this.directory));
            subDirectories = walk.filter(Files::isDirectory).toList();
            for (Path p : subDirectories) {
                String[] parts = p.getFileName().toString().split("_");
                var person = new Person(Integer.parseInt(parts[0]), parts[1]);
                List<Document> pathDocs = new ArrayList<>();
                pathDocs = loadPathDocuments(p);
                documents.put(person, pathDocs);
            }
        } catch (IOException e) {
            System.err.println("Failed to load documents: " + e.getMessage());
        }

    }

    public List<Document> loadPathDocuments(Path path) {
        List<Document> result = new ArrayList<>();
        try (Stream<Path> stream = Files.walk(path)) {
            result = stream.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .map(Document::new)
                    .toList();
        } catch (IOException e) {
            System.err.println("Failed to load documents: " + e.getMessage());
        }
        return result;
    }

    public void addDocument(Person person, Document document) throws IllegalArgumentException {
        if (!documents.containsKey(person)) {
            throw new IllegalArgumentException("Person not found in documents map");
        }
        Path personDirectory = Paths.get(this.directory + "/" + person.id() + "_" + person.name());
        Path filePath = personDirectory.resolve(document.file().getName());
        try {
            Files.copy(document.file().toPath(), filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File written successfully: " + filePath);

            loadDocuments();
        } catch (IOException e) {
            System.err.println("Failed to write file: " + e.getMessage());
        }
    }

    public void addPerson(Person person) {
        if (!documents.containsKey(person)) {
            Path repoName = Paths.get(this.directory + "/" + person.id()+ "_" + person.name());
            try {
                Files.createDirectory(repoName);
                System.out.println("Directory created successfully!");
            } catch (IOException e) {
                System.err.println("Failed to create directory: " + e.getMessage());
            }
            loadDocuments();
        }
    }

    public void deletePerson(Person person) {
        if (!documents.containsKey(person)) {
            throw new IllegalArgumentException("Person doesn't exist: " + person.name());
        }
        Path directoryToDelete = Paths.get(this.directory + "/" + person.id()+ "_" + person.name());
        try {
            Files.walk(directoryToDelete)
                    .sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            loadDocuments();
            System.out.println("Directory deleted successfully!");
        } catch (IOException e) {
            System.err.println("Failed to delete directory: " + e.getMessage());
        }
    }


    public void printAllDocuments() {
        for (Person person : documents.keySet()) {
            System.out.println(person.name());
            for (Document document : documents.get(person))
                System.out.print(document.file().getName() + " ");
            System.out.println();
        }
    }
    public Map<String, Set<String>> readExcelFile(String filePath) throws DocumentRepositoryException {
        Map<String, Set<String>> personAbilities = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0); // get the only sheet - 0

            for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
                // For - starts with index 1 because first row is for names of the columns.
                // Nr.crt.	Name	  Abilities
                // 1	   Person1	   DevOps
                // 2	   Person2	   Frontend
                //eg from the abilities.xlsx
                Row row = sheet.getRow(rowIndex);

                String personName = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().trim();
                Set<String> abilities = new HashSet<>();
                for (int i = 2; i < row.getLastCellNum(); i++) { // First column is for index
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String[] hobbies = cell.toString().trim().split(", "); // split the abilities, because is Excel File are written with commas.
                    for (String hobby : hobbies) {
                        if (!hobby.isEmpty()) {
                            abilities.add(hobby);
                        }
                    }
                }
                personAbilities.put(personName, abilities);
            }
        } catch (IOException e) {
            throw new DocumentRepositoryException("Error reading: " + e.getMessage());
        }
        return personAbilities;
    }
}
