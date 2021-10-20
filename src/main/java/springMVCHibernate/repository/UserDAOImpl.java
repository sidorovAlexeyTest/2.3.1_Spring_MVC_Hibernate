//package springMVCHibernate.dao;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import springMVCHibernate.model.User;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.sql.Date;
//import java.util.List;
//
//@Repository
//public class UserDAOImpl implements UserDAO {
//
//    @PersistenceContext(name = "persistence")
//    private EntityManager entityManager;
//
//    @Override
//    public User read(long id) {
//        User user = entityManager.find(User.class, id);
//        return user != null ? user : new User();
//    }
//
//    @Override
//    public void update(long id, String name, String surname, Date birthdate) {
//        if(id > 0) entityManager.merge(new User(id, name, surname, birthdate));
//        else entityManager.persist(new User(name, surname, birthdate));
//    }
//
//    @Override
//    public void delete(long id) {
//        User user = new User();
//        user.setId(id);
//        entityManager.remove(user);
//    }
//
//    @Override
//    @Transactional
//    public List<User> readAll() {
//        return entityManager.createQuery("FROM User").getResultList();
//    }
//}
