package web.test.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.test.dao.UserDao;
import web.test.model.User;

import java.util.List;

/**
 * Created by tania on 15.04.17.
 */
@Repository
public class UserDAOImpl implements UserDao {
    @Autowired
    public SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    @Transactional
    public void create(User user) {
        Session session = getSession();
        session.save(user);
    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(User model) {

    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<User> getAll() {
        Session session = getSession();
        return session.createQuery("from User").list();
    }

    @Override
    @Transactional
    public User getUserByName(String name) {
        Session session = getSession();
        User user = (User) session.createQuery("from User as u where u.name = '" + name + "'").uniqueResult();
        return user;
    }

    private Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

}
