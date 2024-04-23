package classes;

import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.PatonCycleBase;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Thread {
    public GameLogic game;
    public String name;
    public int turn;
    public boolean running = true;

    public ArrayList<Pair> playerTokens = new ArrayList<>();

    Player(String name, int turn) {
        this.name = name;
        this.turn = turn;
    }

    @Override
    public void run() {
        while(running) {
            synchronized (game) {
                while (turn != game.currentTurn && !game.isBagEmpty && game.isTimeFinished.get()==false) {
                    try {
                        game.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(game.isBagEmpty||game.isTimeFinished.get()==true)
                {
                    return;
                }
                var tokens = game.extractTokens(1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("Player: " + this.name + " extracted: " + "!!!!!!!!!!!!!!!!" +game.isTimeFinished);
                playerTokens.addAll(tokens);
                for (var token : tokens) {
                    System.out.print("(" + token.first + " " + token.second + ") ");
                }
                System.out.println();
                if(maxCycleLength()==game.tokenCount)
                {
                    System.out.println("Player: " + this.name + " has won the game!");
                    game.isBagEmpty = true;
                    game.notifyAll();
                    return;
                }else {
                    game.currentTurn = (game.currentTurn + 1) % game.playerThreads.size();
                }
                game.notifyAll();
            }
        }
    }
    public int maxCycleLength(){
        Graph<Integer, DefaultEdge> undirectedGraph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        for(var pair : playerTokens)
        {
            if(!undirectedGraph.containsVertex(pair.first))
                undirectedGraph.addVertex(pair.first);

            if(!undirectedGraph.containsVertex(pair.second))
                undirectedGraph.addVertex(pair.second);
        }

        for(var pair : playerTokens)
        {
            undirectedGraph.addEdge(pair.first, pair.second);
        }

        PatonCycleBase<Integer, DefaultEdge> cycleDetector = new PatonCycleBase<>(undirectedGraph);

        int currentMaxCycle = 0;
        for(var cycle : cycleDetector.getCycleBasis().getCycles())
        {
            if(cycle.size() > currentMaxCycle)
            {
                currentMaxCycle = cycle.size();
            }
        }
        return currentMaxCycle;

    }
}