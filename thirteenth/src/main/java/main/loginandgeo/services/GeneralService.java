package main.loginandgeo.services;

import main.loginandgeo.dto.AccountDTO;
import org.springframework.stereotype.Service;

@Service
public interface GeneralService {
    void addAccount(AccountDTO accountDTO);

}
