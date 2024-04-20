package classes;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class Player implements Runnable {
    private String name;
    private Game game;
    private boolean running = true;
    private List<Pair> tokens = new ArrayList<>();
    private List<List<Pair>> sequences = new ArrayList<>();
    private List<Pair> edges = new ArrayList<>();

    public Player(String name, Game game) {
        this.name = name;
        this.game = game;
    }


    public void run() {
        while (running) {
            Pair token = game.getBag().extractToken();
            if (token != null) {
                tokens.add(token);
                System.out.println(name + " extracted: " + token.getNumber1() + ", " + token.getNumber2());
                addEdges();
                if (calculateMaxCycleLength() == game.getN()) {
                    System.out.println(name + " won!");
                    stop();
                }
            } else {
//                System.out.println(calculateMaxCycleLength());
                findAndRemoveCycles();
                System.out.println(name + " score: " + getScore());
                stop();
            }
        }
    }
    public void stop() {
        running = false;
    }


    public void addEdges() {
        for (int i = 0; i < tokens.size(); i++) {
            for (int j = i + 1; j < tokens.size(); j++) {
                Pair token1 = tokens.get(i);
                Pair token2 = tokens.get(j);
                if (token1.getNumber1() == token2.getNumber1() ||
                        token1.getNumber1() == token2.getNumber2() ||
                        token1.getNumber2() == token2.getNumber1() ||
                        token1.getNumber2() == token2.getNumber2()) {
                    edges.add(new Pair(i, j)); // Add an edge between tokens i and j
                }
            }
        }
    }

    public int calculateMaxCycleLength() {
        boolean[] visited = new boolean[tokens.size()];
        int maxCycleLength = 0;

        for (int i = 0; i < tokens.size(); i++) {
            if (!visited[i]) {
                maxCycleLength = Math.max(maxCycleLength, DFS(i, -1, 0, visited));
            }
        }
        return maxCycleLength;
    }

    private int DFS(int v, int parent, int length, boolean[] visited) {
        visited[v] = true;
        int maxCycleLength = length;

        for (Pair edge : edges) {
            int i = edge.getNumber1();
            int j = edge.getNumber2();
            if (i == v || j == v) {
                int adj = (i == v) ? j : i;
                if (!visited[adj]) {
                    maxCycleLength = Math.max(maxCycleLength, DFS(adj, v, length + 1, visited));
                } else if (adj != parent) {
                    maxCycleLength = Math.max(maxCycleLength, length);
                }
            }
        }

        visited[v] = false;
        return maxCycleLength;
    }

    public List<Pair> calculateMaxCycle() {
        boolean[] visited = new boolean[tokens.size()];
        List<Pair> currentCycle = new ArrayList<>();
        List<Pair> maxCycle = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++) {
            if (!visited[i]) {
                currentCycle.clear();
                DFS2(i, -1, currentCycle, visited, maxCycle);
            }
        }
//        System.out.println(maxCycle+" "+maxCycle.size());
        if(maxCycle.size()>=3)
        return new ArrayList<>(maxCycle);
        else return new ArrayList<>();
    }

    private void DFS2(int v, int parent, List<Pair> currentCycle, boolean[] visited, List<Pair> maxCycle) {
        visited[v] = true;
        currentCycle.add(tokens.get(v));

        for (Pair edge : edges) {
            int i = edge.getNumber1();
            int j = edge.getNumber2();
            if (i == v || j == v) {
                int adj = (i == v) ? j : i;
                if (!visited[adj]) {
                    DFS2(adj, v, currentCycle, visited, maxCycle);
                } else if (adj != parent && currentCycle.size() >= 3) {
                    if (currentCycle.size() > maxCycle.size()) {
                        maxCycle.clear();
                        maxCycle.addAll(currentCycle);
                    }
                }
            }
        }

        visited[v] = false;
        currentCycle.remove(tokens.get(v));
    }

    public void findAndRemoveCycles() {
        while (true) {
            List<Pair> cycle = new ArrayList<>(calculateMaxCycle());
            if (cycle.isEmpty()) {
                break;
            }
            sequences.add(cycle);
            for (Pair token : cycle) {
                int i = tokens.indexOf(token);
                tokens.remove(token);
                List<Pair>notToRemove=new ArrayList<>();
               for(i=0;i<edges.size();i++)
               {
                   Pair edge = edges.get(i);
                   if (!(edge.getNumber1() == i || edge.getNumber2() == i)){
                          notToRemove.add(edge);
                   }
               }
                edges.clear();
                edges.addAll(notToRemove);
            }
        }
    }

    public int getScore() {
        return sequences.stream().mapToInt(List::size).sum();
    }
}