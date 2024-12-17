package main.repositories;

import main.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<CustomUser, Long> {

    CustomUser findByLogin(@Param("login")String login);

    boolean existsByLogin(@Param("login")String login);
}
