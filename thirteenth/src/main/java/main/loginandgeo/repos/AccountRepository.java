package main.loginandgeo.repos;

import main.loginandgeo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

boolean existsByEmail(String email);
Account findByEmail(String email);

}
