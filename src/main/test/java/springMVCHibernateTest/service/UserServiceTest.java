package springMVCHibernateTest.service;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import springMVCHibernate.model.User;
import springMVCHibernate.service.UserService;
import springMVCHibernateTest.config.TestPersistenceConfig;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Date;
import java.util.List;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestPersistenceConfig.class)
@WebAppConfiguration
@Transactional
public class UserServiceTest {

    private static User user1, user2;

    @Autowired
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private UserService userServiceImpl;

    @BeforeClass
    public static void setUsers() {
        user1 = new User("Ivan", "Ivanov", new Date(1990, 5, 9));
        user2 = new User("Petr", "Petrov", new Date(1991, 4, 10));
    }

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @After
    public void clearAfterTest() {
        userServiceImpl.clearTable();
    }

    @Test
    public void testSaveUser() throws Exception {
        userServiceImpl.add(
                user1.getName(),
                user1.getSurname(),
                user1.getBirthdate()
        );
        User actualUser = userServiceImpl.readAll().get(0);
        Assert.assertEquals(user1, actualUser);
    }

    @Test
    public void testDeleteUser() {
        userServiceImpl.add(
                user1.getName(),
                user1.getSurname(),
                user1.getBirthdate()
        );
        userServiceImpl.add(
                user2.getName(),
                user2.getSurname(),
                user2.getBirthdate()
        );
        long id1 = userServiceImpl.readAll().get(0).getId();
        long id2 = userServiceImpl.readAll().get(1).getId();
        userServiceImpl.delete(id1);
        userServiceImpl.delete(id2);
        List<User> list = userServiceImpl.readAll();
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void updateUser(){
        userServiceImpl.add(
                user1.getName(),
                user1.getSurname(),
                user1.getBirthdate()
        );
        long id1 = userServiceImpl.readAll().get(0).getId();
        userServiceImpl.update(
                id1,
                user2.getName(),
                user2.getSurname(),
                user2.getBirthdate()
        );
        Assert.assertEquals(user2, userServiceImpl.read(id1));
    }
}
