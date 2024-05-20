package DataBase;

import JPAObjects.Book;
import RepositoryPackage.BookRepository;
import jakarta.persistence.EntityManager;
import DataBase.myLogger;

import java.lang.module.FindException;

public class SolveRunner {
    private static final myLogger logger = new myLogger();

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try {
            EntityManager entityManager = SingletonEntityManagerFactory.getEntityManagerFactory().createEntityManager();
            BookRepository bookRepository = new BookRepository(entityManager);
            ConstraintSolver constraintSolver = new ConstraintSolver(bookRepository, 2, 10);
            logger.info("The optimal set of books is: ");
            for (Book book : constraintSolver.generateOptimalSet()) {
                logger.info(book.toString());
            }
        } catch (FindException ex) {
            logger.logException(ex);
        } finally {
            long endTime = System.currentTimeMillis();
            logger.logExecutionTime(startTime, endTime);
        }
    }
}
