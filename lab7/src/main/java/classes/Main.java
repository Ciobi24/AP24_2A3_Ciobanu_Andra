package classes;

//la clasa care implementeaza runnable se face functia run dar se apeleaza functia START!!!!!!!!
/*          Thread thread = new Thread(player);
            thread.start();                     */
public class Main {
    public static void main(String[] args)
    {
        GameLogic logic = new GameLogic();
        logic.generateTokens(100);

        Timekeeper timeKeeper = new Timekeeper(60);
        timeKeeper.start();
        Player player1 = new Player("P1", 0);
        Player player2 = new Player("P2", 1);
        Player player3 = new Player("P3", 2);

        logic.addPlayer(player1);
        logic.addPlayer(player2);
        logic.addPlayer(player3);

        logic.startThreads();

    }
}