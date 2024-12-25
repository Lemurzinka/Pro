package main.loginandgeo.services;

import main.loginandgeo.dto.AccountDTO;
import main.loginandgeo.model.Account;
import main.loginandgeo.repos.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GeneralServiceImpl implements GeneralService {


    private final AccountRepository accountRepository;

    public GeneralServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    @Override
    public void addAccount(AccountDTO accountDTO) {
        if (accountRepository.existsByEmail(accountDTO.getEmail())) {
            return;
        }
        Account account = Account.fromDTO(accountDTO);

        accountRepository.save(account);
    }

}
