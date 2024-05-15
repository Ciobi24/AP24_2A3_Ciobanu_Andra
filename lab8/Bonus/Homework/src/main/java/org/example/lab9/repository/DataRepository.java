package org.example.lab9.repository;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.lab9.DatabaseUtils;

public abstract class DataRepository<T, ID extends Serializable> {
    // Entity class for subclasses
    protected abstract Class<T> getEntityClass();
    private static final Logger LOGGER = Logger.getLogger(DataRepository.class.getName());

    // CRUD Operations
    private boolean runWithRollback(T entity, Consumer<T> consumer) {
        try {
            beginTransaction();
            consumer.accept(entity);
            commit();
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in runWithRollback method", e);
            rollback();
            return false;
        }
    }

    public T findById(ID id) {
        try {
            return getEntityManager().find(getEntityClass(), id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in findById method", e);
            return null;
        }
    }

    public boolean delete(T entity) {
        return runWithRollback(entity, (e) -> {
            try {
                getEntityManager().remove(e);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Error occurred in delete method", ex);
            }
        });
    }

    public boolean save(T entity) {
        return runWithRollback(entity, (e) -> {
            try {
                getEntityManager().persist(e);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Error occurred in save method", ex);
            }
        });
    }

    // EntityManager Specific
    private final EntityManager entityManager = DatabaseUtils.getInstance().getEntityManager();
    private EntityTransaction entityTransaction;

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    private void beginTransaction() {
        try {
            entityTransaction = getEntityManager().getTransaction();
            entityTransaction.begin();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in beginTransaction method", e);
        }
    }

    private void commit() {
        try {
            entityTransaction.commit();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in commit method", e);
        }
    }

    private void rollback() {
        try {
            entityTransaction.rollback();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in rollback method", e);
        }
    }
}