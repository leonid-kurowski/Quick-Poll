package ua.com.foxminded.quickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.foxminded.quickpoll.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
