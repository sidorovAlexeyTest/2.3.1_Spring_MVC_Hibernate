package springMVCHibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springMVCHibernate.repository.UserRepository;
import springMVCHibernate.model.User;

import java.sql.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(String name, String surname, Date birthdate) {
        return userRepository.saveAndFlush(new User(name, surname, birthdate));
    }

    @Override
    public User read(long id) {
        return userRepository.getById(id);
    }

    @Override
    public User update(long id, String name, String surname, Date birthdate) {
        System.out.println(id);
        return userRepository.saveAndFlush(new User(id, name, surname, birthdate));
    }

    @Override
    public void delete(long id) {
        User user = new User();
        user.setId(id);
        userRepository.delete(user);
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }
}
