package classes;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.alg.cycle.PatonCycleBase;

import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.HawickJamesSimpleCycles;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameLogic
{
    static public GameLogic instance = null;
    public final Object monitor = new Object();
    public int currentTurn = 0;
    public boolean isFirstThreadEntered = false;
    List<Player> playerThreads = new ArrayList<>();
    Random rand = new Random();
    int tokenCount;
    AtomicBoolean isTimeFinished;
    ArrayList<Pair> tokens = new ArrayList<>();

    boolean isBagEmpty = false;

    public boolean isInUse = false;

    private GameLogic(AtomicBoolean isTimeFinished) {
        this.isTimeFinished = isTimeFinished;
    }
    public static  GameLogic getInstance(AtomicBoolean isTimeFinished)
    {
        if(instance == null)
        {
            instance = new GameLogic(isTimeFinished);
        }
        return instance;
    }

    public void generateTokens(int tokenCount)
    {
        this.tokenCount = tokenCount;
        for (int i = 1; i <= tokenCount; i++) {
            for (int j = i + 1; j <= tokenCount; j++) {
                tokens.add(new Pair(i, j));
            }
        }
        Collections.shuffle((List<?>) tokens);
    }

    public synchronized List<Pair> extractTokens(int count)
    {
        ArrayList<Pair> extractedTokens = new ArrayList<>();
        for(int i = 0; i < count; i++)
        {
            if(tokens.isEmpty())
            {
                break;
            }
            int randomToken = rand.nextInt(tokens.size());
            extractedTokens.add(tokens.get(randomToken));
            tokens.remove(randomToken);
        }
        if(tokens.isEmpty()||isTimeFinished.get())
        {
            findWinner();
            this.isBagEmpty = true;
            this.notifyAll();
        }
        return extractedTokens;
    }

    public void findWinner()
    {
        Player winnerPlayer = null;
        int maxCycle = -1;
        for(var playerThread : playerThreads)
        {
            Graph<Integer, DefaultEdge> undirectedGraph = new DefaultUndirectedGraph<>(DefaultEdge.class);
            for(var pair : playerThread.playerTokens)
            {
                if(!undirectedGraph.containsVertex(pair.first))
                    undirectedGraph.addVertex(pair.first);

                if(!undirectedGraph.containsVertex(pair.second))
                    undirectedGraph.addVertex(pair.second);
            }

            for(var pair : playerThread.playerTokens)
            {
                undirectedGraph.addEdge(pair.first, pair.second);
            }

            PatonCycleBase<Integer, DefaultEdge> cycleDetector = new PatonCycleBase<>(undirectedGraph);

            System.out.println("For player: " + playerThread.name + " found: " + cycleDetector.getCycleBasis().getCycles().size() + " cycles.");
            System.out.println(cycleDetector.getCycleBasis().getCycles());

            int currentMaxCycle = 0;
            for(var cycle : cycleDetector.getCycleBasis().getCycles())
            {
                if(cycle.size() > currentMaxCycle)
                {
                    currentMaxCycle = cycle.size();
                }
            }

            System.out.println(playerThread.name +"'s longest cycle: " + currentMaxCycle);

            if(currentMaxCycle >= maxCycle)
            {
                maxCycle = currentMaxCycle;
                winnerPlayer = playerThread;
            }

        }

        if(maxCycle == 0 || winnerPlayer == null)
        {
            System.out.println("No winner!");
        }
        else
            System.out.println("The player: " + winnerPlayer.name + " has won the game!");

        System.exit(0);
    }
    public void addPlayer(Player player)
    {
        player.game = this;
        playerThreads.add(player);
    }

    public void startThreads()
    {
        for(var thread : playerThreads)
        {
            thread.start();
        }
    }
}
