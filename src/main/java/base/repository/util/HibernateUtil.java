package base.repository.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {
    private final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("bus");

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}