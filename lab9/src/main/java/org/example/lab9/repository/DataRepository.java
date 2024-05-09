package org.example.lab9.repository;

import java.io.Serializable;
import java.util.function.Consumer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.lab9.DatabaseUtils;

public abstract class DataRepository<T, ID extends Serializable> {
    // Entity class for subclasses
    protected abstract Class<T> getEntityClass();

    // CRUD Operations
    private boolean runWithRollback(T entity, Consumer<T> consumer) {
        try {
            beginTransaction();
            consumer.accept(entity);
            commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
            return false;
        }
    }

    public T findById(ID id) {
        return getEntityManager().find(getEntityClass(), id);
    }

    public boolean delete(T entity) {
        return runWithRollback(entity, (e) -> getEntityManager().remove(e));
    }

    public boolean save(T entity) {
        return runWithRollback(entity, (e) -> getEntityManager().persist(e));
    }


    // // EntityManager Specific
    private final EntityManager entityManager = DatabaseUtils.getInstance().getEntityManager();
    private EntityTransaction entityTransaction;

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    private void beginTransaction() {
        entityTransaction = getEntityManager().getTransaction();
        entityTransaction.begin();
    }

    private void commit() {
        entityTransaction.commit();
    }

    private void rollback() {
        entityTransaction.rollback();
    }
}
