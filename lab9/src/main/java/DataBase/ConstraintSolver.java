package DataBase;

import JPAObjects.Book;
import RepositoryPackage.BookRepository;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.BoolVar;


import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods for finding a set of at least
 * k books having titles that start with the same letter,
 * and they are published in the same period of time.
 * For any two books in the result, the difference between their publish years must not exceed a given value p.
 * <p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Constraint_programming">Constraint Based Programming</a>
 */
public class ConstraintSolver {
    private final BookRepository bookRepository;
    private final Model model;
    private final int maxDelta;
    private final int minCardinality;

    private List<Book> bookList;

    public ConstraintSolver(BookRepository bookRepository, int maxDelta, int minCardinality) {
        this.bookRepository = bookRepository;
        this.model = new Model("Book Constraint Problem");
        this.maxDelta = maxDelta;
        this.minCardinality = minCardinality;
        this.bookList = new ArrayList<>();
    }

    private int getRelationshipIndex(Book firstBook, Book secondBook) {
        if (firstBook.getTitle().charAt(0) != secondBook.getTitle().charAt(0)) {
            return 1;
        }
        if (Math.abs(firstBook.getPublishYear() - secondBook.getPublishYear()) > maxDelta) {
            return 1;
        }
        return 2;
    }

    private List<Book> retrieveOptimalSet(Solver solver, BoolVar[] vars) {
        List<Book> optimalSet = new ArrayList<>();
        if (solver.solve()) {
            for (int i = 0; i < vars.length; ++i) {
                if (vars[i].getValue() == 1) {
                    optimalSet.add(this.bookList.get(i));
                }
            }
        }
        return optimalSet;
    }

    public List<Book> generateOptimalSet() throws FindException {
        this.bookList = bookRepository.findAll();
        int bookCount = bookList.size();

        BoolVar[] vars = model.boolVarArray("vars", bookCount);
        for (int i = 0; i < bookCount; ++i) {
            for (int j = i + 1; j < bookCount; j++) {
                int constrainedSum = getRelationshipIndex(bookList.get(i), bookList.get(j));
                if (constrainedSum == 2) {
                    continue;
                }
                model.arithm(vars[i], "+", vars[j], "<=", constrainedSum).post();
            }
        }

        model.sum(vars, ">=", minCardinality).post();
        return retrieveOptimalSet(model.getSolver(), vars);
    }
}

