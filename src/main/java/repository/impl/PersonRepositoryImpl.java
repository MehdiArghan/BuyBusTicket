package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Person;
import jakarta.persistence.EntityManager;
import repository.PersonRepository;

public class PersonRepositoryImpl extends BaseRepositoryImpl<Long, Person> implements PersonRepository {
    public PersonRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Person> getEntityClass() {
        return Person.class;
    }
}
