package web.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;
import web.model.User;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Repository

public class UserDaoImpl implements UserDao {

    private static final Logger log = Logger.getLogger(UserDaoImpl.class.getName());
    EntityManagerFactory emf;
    EntityManager em;

    public UserDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
        em = emf.createEntityManager();
    }

    @Override
    public void addUser(User user) {

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        log.info(" FROM USERDAO: added user: " + user.toString());
    }

    @Override
    public List<?> getUsersList() {
        return em.createQuery("select u from User u").getResultList();
    }

    @Override
    public void updateUser(int id, String name,String lastname, int age) {
        User dbUser = em.find(User.class, id) ;
        if (dbUser != null) {
            try {
                em.getTransaction().begin();
                dbUser.setName(name);
                dbUser.setLastname(lastname);
                dbUser.setAge(age);
                em.getTransaction().commit();
                log.info(" FROM USERDAO: updated user: " + dbUser);
            } catch (RuntimeException e) {
                em.getTransaction().rollback();
                log.log(Level.WARNING, e.getMessage());
            }
        }
    }

    @Override
    public void deleteUser(int id) {
        em.getTransaction().begin();
        em.remove(em.find(User.class, id));
        em.getTransaction().commit();
        log.info(" FROM USERDAO: deleted user");
    }

    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }
}
