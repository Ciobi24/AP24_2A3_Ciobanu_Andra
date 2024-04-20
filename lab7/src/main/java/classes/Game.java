package classes;

import lombok.Getter;

import java.util.*;

@Getter
public class Game {
    private final Bag bag;
    private final List<Player> players = new ArrayList<>();
private int n;
    public Game(int n) {
        this.bag = new Bag(n);
        this.n=n;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void play() {
        List<Thread> threads = new ArrayList<>();
        for (Player player : players) {
            Thread thread = new Thread(player);
            thread.start();
            threads.add(thread);
        }
    }

}