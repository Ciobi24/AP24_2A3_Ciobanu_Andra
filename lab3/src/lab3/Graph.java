package lab3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    private int V;   // No. of vertices
    private List<Integer>[] adj; //Adjacency List

    //Constructor
    public Graph(int v) {
        V = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new ArrayList();
    }

    //Function to add an edge into the graph
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v); //Graph is undirected
    }

    public int getV() {
        return V;
    }

    public List<Integer>[] getAdj() {
        return adj;
    }

    public void constructGraph(List<Attraction> attractions) {
        for (int i = 0; i < attractions.size(); i++) {
            for (int j = i + 1; j < attractions.size(); j++) {
                if (attractions.get(i).getClass().equals(attractions.get(j).getClass())) {
                    this.addEdge(i, j);
                }
            }
        }
    }

    public void DSatur(Trip trip, int[] result) {
        // Initialize all vertices as uncolored
        Arrays.fill(result, -1);
        // Assign the first color to the first vertex
        result[0] = 0;

        // A temporary array to store the available colors
        boolean[] available = new boolean[this.getV()];
        Arrays.fill(available, true);

        // Store the degree of saturation of each vertex
        int[] saturation = new int[this.getV()];

        for (int u = 1; u < this.getV(); u++) {
            // Process all adjacent vertices and update their saturation
            for (int i : this.getAdj()[u])
                if (result[i] == -1)
                    saturation[i]++;

            // Find the vertex with the maximum saturation and degree
            int maxSat = -1, maxDeg = -1, v = -1;
            for (int i = 0; i < this.getV(); i++) {
                if (result[i] == -1 && (v == -1 || saturation[i] > maxSat || (saturation[i] == maxSat && this.getAdj()[i].size() > maxDeg))) {
                    maxSat = saturation[i];
                    maxDeg = this.getAdj()[i].size();
                    v = i;
                }
            }
            for (int i : this.getAdj()[v])
                if (result[i] != -1)
                    available[result[i]] = false;
            // Find the first available color
            int cr;
            for (cr = 0; cr < this.getV(); cr++)
                if (available[cr]) {
                    Attraction attraction = trip.getAttractions().get(v);
                    LocalDate visitDate = trip.getStart().plusDays(cr);
                    if (((Visitable) attraction).getTimetable().get(visitDate) != null) {
                        break;
                    }
                }

            result[v] = cr; // Assign the found color

            // Reset the values back to true for the next iteration
            Arrays.fill(available, true);
        }
    }

    public void RLF(Trip trip, int[] result) {
        // Initialize all vertices as uncolored
        Arrays.fill(result, -1);

        int color = 0;
        List<Integer> uncolored = new ArrayList<>();
        for (int i = 0; i < this.getV(); i++)
            uncolored.add(i);

        while (!uncolored.isEmpty()) {
            List<Integer> U = new ArrayList<>(uncolored);
            List<Integer> C = new ArrayList<>();

            while (!U.isEmpty()) {
                int v = -1;
                for (int u : U)
                    if (v == -1 || this.getAdj()[u].size() > this.getAdj()[v].size())
                        v = u;

                U.remove((Integer) v);
                C.add(v);
                uncolored.remove((Integer) v);

                for (int i = U.size() - 1; i >= 0; i--)
                    if (this.getAdj()[v].contains(U.get(i)))
                        U.remove(i);
            }

            for (int v : C) {
                Attraction attraction = trip.getAttractions().get(v);
                LocalDate visitDate = trip.getStart().plusDays(color);
                if (((Visitable) attraction).getTimetable().get(visitDate) != null) {
                    result[v] = color;
                }
            }

            color++;
        }
    }

}