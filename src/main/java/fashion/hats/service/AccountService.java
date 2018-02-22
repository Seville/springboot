package fashion.hats.service;

import fashion.hats.entity.Accounts;
import fashion.hats.repo.AccountRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "accountService")
public class AccountService {

    @Autowired
    AccountRepositoryDAO accountRepo;

    public Iterable<Accounts> getAllAccounts() {
        return accountRepo.getAllAccounts();
    }

    public Iterable<Accounts> getAccountsByFirstName(String firstname) {
        return accountRepo.getAccountsByFirstName(firstname);
    }

    public void createAccount(Accounts account) {
        accountRepo.createNewAccount(account);
    }

    public void removeAccount(String id) {
        accountRepo.removeAccount(id);
    }

    public void updateAccount(Accounts account) {
        accountRepo.updateAccount(account);
    }
}
