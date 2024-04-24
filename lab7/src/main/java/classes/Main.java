package classes;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean isTimeFinished = new AtomicBoolean(false);
        GameLogic logic = GameLogic.getInstance(isTimeFinished);
        logic.generateTokens(10);

        Timekeeper timeKeeper = new Timekeeper(6, isTimeFinished);
        timeKeeper.setDaemon(true);

        Player player1 = new Player("P1", 0);
        Player player2 = new Player("P2", 1);
        Player player3 = new Player("P3", 2);

        logic.addPlayer(player1);
        logic.addPlayer(player2);
        logic.addPlayer(player3);

        timeKeeper.start();
        logic.startThreads();

        timeKeeper.join();
        logic.findWinner();
    }
}
