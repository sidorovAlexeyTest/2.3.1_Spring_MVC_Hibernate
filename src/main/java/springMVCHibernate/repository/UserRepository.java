package springMVCHibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springMVCHibernate.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
