package org.example.lab9.repository;

import org.example.lab9.model.PublishingHouse;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PublishingHouseRepository extends DataRepository<PublishingHouse, Integer> {
    private static final Logger LOGGER = Logger.getLogger(PublishingHouseRepository.class.getName());

    @Override
    protected Class<PublishingHouse> getEntityClass() {
        return PublishingHouse.class;
    }

    public void create(PublishingHouse publishingHouse) {
        try {
            save(publishingHouse);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in create method", e);
        }
    }

    public PublishingHouse findById(Integer id) {
        try {
            return super.findById(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in findById method", e);
            return null;
        }
    }

    public List<PublishingHouse> findByName(String name) {
        try {
            TypedQuery<PublishingHouse> query = getEntityManager().createNamedQuery("PublishingHouse.findByName", PublishingHouse.class);
            query.setParameter("name", name);
            return query.getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in findByName method", e);
            return null;
        }
    }
}