package main.telebotbank.repo;

import main.telebotbank.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByTelegramId(Long telegramId);

    Users findByNumber(String Number);

}
