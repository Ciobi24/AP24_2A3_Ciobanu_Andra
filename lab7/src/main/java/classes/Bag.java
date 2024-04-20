package classes;


import lombok.Getter;

import java.util.*;
@Getter

public class Bag {
    private final Queue<Pair> tokens;

    public Bag(int n) {
        this.tokens = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                tokens.add(new Pair(i, j));
            }
        }
        Collections.shuffle((List<?>) tokens);
    }

    public synchronized Pair extractToken() {
        if (tokens.isEmpty()) {
            return null;
        }
        return tokens.poll();
    }

    public boolean isEmpty() {
        return tokens.isEmpty();
    }
}