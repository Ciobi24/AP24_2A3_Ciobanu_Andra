package DataBase;

import java.util.logging.*;

/**
 * The myLogger class
 */

public class myLogger {

    private final Logger logger;

    /**
     * The constructor for myLogger class
     */
    public myLogger() {
        this.logger = Logger.getLogger("myLogger");

        try {
            // Creează un FileHandler care scrie log-urile în fișierul "App.log"
            FileHandler fileHandler = new FileHandler("App.log");

            // Setează un formatter simplu pentru mesajele de log
            fileHandler.setFormatter(new SimpleFormatter());

            // Adaugă handler-ul de fișier la logger
            logger.addHandler(fileHandler);

            // Setează nivelul de logare la Level.ALL pentru a captura toate mesajele de log
            logger.setLevel(Level.ALL);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating file handler", e);
        }
    }

    /**
     * This initializes messages at info level
     * @param message The messages the logger will write
     */
    public void info(String message) {this.logger.info(message);}


    /**
     * This writes messages for warnings
     * @param message The messages the logger will write
     */
    public void warning(String message) {
        this.logger.warning(message);
    }

    /**
     * This writes messages with a severe level
     * @param message The messages the logger will write
     */
    public void severe(String message) {
        logger.severe(message);
    }

    /**
     * This writes exceptions at a severe level
     * @param e The exception the logger will throw
     */
    public void logException(Exception e){this.logger.log(Level.SEVERE,"Exception: " + e.getMessage());}

    /**
     * This gets the execution time for a certain operation
     * @param startTime The starting time for an operation
     * @param endTime The ending time for an operation
     */
    public void logExecutionTime(long startTime, long endTime) {
        long executionTime = endTime - startTime;
        this.logger.info("The operation took: " + executionTime + " ms");
    }
}