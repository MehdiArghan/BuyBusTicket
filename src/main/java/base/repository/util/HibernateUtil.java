package base.repository.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("bus");

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}