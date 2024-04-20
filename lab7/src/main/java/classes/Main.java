package classes;

//la clasa care implementeaza runnable se face functia run dar se apeleaza functia START!!!!!!!!
/*          Thread thread = new Thread(player);
            thread.start();                     */
public class Main {
    public static void main(String args[]) {
        Main main = new Main();
        main.compulsory();
    }

    public void compulsory() {
        Game game = new Game(4);
        game.addPlayer(new Player("P1", game));
        game.addPlayer(new Player("P2", game));
//        game.addPlayer(new Player("P3", game));
        game.play();
    }
}
