package lab5;


import org.jgrapht.Graph;
import org.jgrapht.alg.clique.BronKerboschCliqueFinder;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
//        main.compulsory();
//        main.homework();
          main.bonus();
    }

    public void compulsory() {
        try {
            Repository repo = new Repository("src/repo");
            var person = new Person(101, "B");
            repo.addPerson(person);
            repo.printAllDocuments();
            File file = new File("src/repo/100_A/cv.txt");
            Document document = new Document(file);
            repo.addDocument(person, document);
            repo.printAllDocuments();
            repo.deletePerson(person);
            repo.printAllDocuments();
        } catch (InvalidRepositoryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void homework() {
        try {
            Repository repo = new Repository("src/repo");
            CommandReader commandReader = new CommandReader(repo);
            commandReader.readAndExecute();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void bonus() {
       try {
           Repository repository = new Repository("src/repo");
           Map<String, Set<String>> excelData = repository.readExcelFile("src/repo/abilities.xlsx");
           Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

           for (String person : excelData.keySet()) {
               graph.addVertex(person);
           }

           // adding edges between persons who have at least one ability in common
           for (String person1 : excelData.keySet()) {
               for (String person2 : excelData.keySet()) {
                   if (!person1.equals(person2)) { // 2 different persons (I cannot have an edge from A to A)
                       Set<String> abilities1 = excelData.get(person1);
                       Set<String> abilities2 = excelData.get(person2);

                       boolean hasCommonAbility = false;
                       for (String ability : abilities1) {  // if it finds a common ability, I can add the edge for the graph.
                           if (abilities2.contains(ability)) {
                               hasCommonAbility = true;
                               break;
                           }
                       }
                       if (hasCommonAbility) {
                           graph.addEdge(person1, person2);
                       }
                   }
               }
           }

           BronKerboschCliqueFinder<String, DefaultEdge> cliqueFinder = new BronKerboschCliqueFinder<>(graph);
           Collection<Set<String>> allCliques = new ArrayList<>();
           cliqueFinder.forEach(allCliques::add);

           int maxCliqueSize = 0;
           List<Set<String>> maxCliques = new ArrayList<>(); // a list with all maximum cliques
           for (Set<String> clique : allCliques) {
               int size = clique.size();
               if (size > maxCliqueSize) {
                   maxCliques.clear();
                   maxCliques.add(clique);
                   maxCliqueSize = size;
               } else if (size == maxCliqueSize) {
                   maxCliques.add(clique);
               }
           }

           // printing the result
           System.out.println("Cliques: ");
           for (Set<String> maxClique : maxCliques) {
               System.out.println(maxClique);
           }
       } catch (InvalidRepositoryException | DocumentRepositoryException e) {
           System.out.println("Error: " + e.getMessage());
       }
    }

}