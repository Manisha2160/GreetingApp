package org.example.dao;

import org.example.model.Greeting;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GreetingDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Greeting save(Greeting greeting) {
        sessionFactory.getCurrentSession().save(greeting);
        return greeting;
    }

    public Greeting findById(long id) {
        return sessionFactory.getCurrentSession().get(Greeting.class, id);
    }

    public List<Greeting> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Greeting", Greeting.class)
                .list();
    }

    public Greeting update(Greeting greeting) {
        sessionFactory.getCurrentSession().update(greeting);
        return greeting;
    }

    public void delete(Greeting greeting) {
        sessionFactory.getCurrentSession().delete(greeting);
    }
}
