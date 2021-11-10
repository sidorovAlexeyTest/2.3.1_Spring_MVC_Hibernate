package springMVCHibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import springMVCHibernate.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
