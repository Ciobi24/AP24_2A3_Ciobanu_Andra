package lab5;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Repository repo = new Repository("src/repo");
        var person = new Person(101,"B");
        repo.addPerson(person);
        repo.printAllDocuments();
        File file = new File("src/repo/100_A/cv.txt");
        Document document = new Document(file);
        repo.addDocument(person,document);
        repo.printAllDocuments();
        repo.deletePerson(person);
        repo.printAllDocuments();
    }
}