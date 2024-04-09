package classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class Player implements Runnable {
    private String name;
    private Game game;
    private boolean running = true;
    private List<Token> tokens = new ArrayList<>();
    private List<List<Token>> sequences = new ArrayList<>();

    public Player(String name, Game game) {
        this.name = name;
        this.game = game;
    }


    public void run() {
        while (running) {
            Token token = game.getBag().extractToken();
            if (token != null) {
                tokens.add(token);
                System.out.println(name + " extracted: " + token.getNumber1() + ", " + token.getNumber2());
//                addTokenToSequences(token);
            }else{
                stop();
            }
        }
    }

    public void stop() {
        running = false;
    }

    private void addTokenToSequences(Token token) {
        for (List<Token> sequence : sequences) {
            Token lastTokenInSequence = sequence.getLast();
            if (token.getNumber1() == lastTokenInSequence.getNumber2() + 1) {
                sequence.add(token);
                return;
            }
        }
        List<Token> newSequence = new ArrayList<>();
        newSequence.add(token);
        sequences.add(newSequence);
    }

    public int getScore() {
        List<List<Token>> sequencesCopy = new ArrayList<>(sequences);
        return sequencesCopy.stream().mapToInt(List::size).max().orElse(0);
    }
}