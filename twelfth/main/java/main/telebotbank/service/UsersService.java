package main.telebotbank.service;

import main.telebotbank.model.Users;
import main.telebotbank.repo.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {this.usersRepository = usersRepository;}

    @Transactional(readOnly = true)
    public Users findByTelegramId(Long telegramId) {return usersRepository.findByTelegramId(telegramId);}

    @Transactional
    public Users findByNumber(String Number) {return usersRepository.findByNumber(Number);}

@Transactional
public void addUser(Users user) {
        usersRepository.save(user);
}

@Transactional()
public void updateUser(Users user) {
        usersRepository.save(user);
}

}
