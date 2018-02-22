package fashion.hats.repo;

import fashion.hats.entity.Accounts;

public interface AccountRepositoryDAO{
    public Iterable<Accounts> getAllAccounts();

    public Iterable<Accounts> getAccountsByFirstName(String firstname);

    public void createNewAccount(Accounts account);

    public void removeAccount(String id);

    public void updateAccount(Accounts account);
}
