package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Person;
import jakarta.persistence.EntityManager;
import repository.PersonRepository;

public class PersonRepositoryImpl extends BaseRepositoryImpl<Long, Person> implements PersonRepository {
    EntityManager entityManager;

    public PersonRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    protected Class<Person> getEntityClass() {
        return Person.class;
    }

    @Override
    public Person findByUserName(String userName) {
        return entityManager.createQuery(" from Person where userName=:USERNAME", Person.class)
                .setParameter("USERNAME", userName)
                .getSingleResult();
    }
}