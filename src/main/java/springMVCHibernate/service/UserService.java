package springMVCHibernate.service;

import springMVCHibernate.model.User;
import java.sql.Date;
import java.util.List;


public interface UserService {
    User add(String name, String surname, Date birthdate);
    User read(long id);
    User update(long id, String name, String surname, Date birthdate);
    void delete(long id);
    List<User> readAll();
    void clearTable();
}
