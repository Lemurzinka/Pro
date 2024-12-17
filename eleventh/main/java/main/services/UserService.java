package main.services;

import main.CustomUser;
import main.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional(readOnly = true)
    public List<CustomUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CustomUser findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
public boolean addUser (String login, String passHash,
                        String email, String phone,
                        String address) {
        if (userRepository.existsByLogin(login)) {
            return false;
        }

        CustomUser user = new CustomUser(login, passHash, email, phone, address);
        userRepository.save(user);
        return true;
    }




}
