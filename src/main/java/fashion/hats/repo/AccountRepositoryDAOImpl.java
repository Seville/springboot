package fashion.hats.repo;

import fashion.hats.constants.AccountsSqlConstants;
import fashion.hats.entity.Accounts;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;


@Repository
public class AccountRepositoryDAOImpl implements AccountRepositoryDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Logger instance
    private static final Logger logger = Logger.getLogger(AccountRepositoryDAOImpl.class);

    public Iterable<Accounts> getAllAccounts() {
        try {
            String getAllAccountsSQL = AccountsSqlConstants.getAllAccountsSql;
            Iterable<Accounts> allAccounts = jdbcTemplate.query(getAllAccountsSQL, new RowMapper<Accounts>() {

                @Override
                public Accounts mapRow(ResultSet rs, int rownumber) throws SQLException {
                    Accounts account = new Accounts();
                    account.setId(rs.getInt(1));
                    account.setFirst_name(rs.getString(2));
                    account.setLast_name(rs.getString(3));
                    account.setMobile(rs.getString(4));
                    account.setHome(rs.getString(5));
                    account.setEmail(rs.getString(6));
                    account.setAddress(rs.getString(7));
                    account.setCity(rs.getString(8));
                    account.setState(rs.getString(9));
                    account.setZip(rs.getString(10));

                    return account;
                }
            });
            return allAccounts;
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("No Data is returned from DB for accounts" + e);
            return null;
        }
    }

    public Iterable<Accounts> getAccountsByFirstName(String firstname) {
        try {
            String getAccountByFirstName = AccountsSqlConstants.getAccountsByFirstNameSql;

            Iterable<Accounts> accountsByFirstName = jdbcTemplate.query(getAccountByFirstName, new Object[]{firstname}, new RowMapper<Accounts>() {
                @Override
                public Accounts mapRow(ResultSet rs, int rownumber) throws SQLException {
                    Accounts account = new Accounts();
                    account.setId(rs.getInt(1));
                    account.setFirst_name(rs.getString(2));
                    account.setLast_name(rs.getString(3));
                    account.setMobile(rs.getString(4));
                    account.setHome(rs.getString(5));
                    account.setEmail(rs.getString(6));
                    account.setAddress(rs.getString(7));
                    account.setCity(rs.getString(8));
                    account.setState(rs.getString(9));
                    account.setZip(rs.getString(10));

                    return account;
                }
            });

            return accountsByFirstName;
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("No Data is returned from DB for accounts by " + firstname + " " + e);
            return null;
        }
    }

    public void createNewAccount(final Accounts account) {
        try {
            String createNewAccountSql = AccountsSqlConstants.createAccountSql;

            KeyHolder holder = new GeneratedKeyHolder();

            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(createNewAccountSql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, account.getFirst_name());
                    ps.setString(2, account.getLast_name());
                    ps.setString(3, account.getMobile());
                    ps.setString(4, account.getHome());
                    ps.setString(5, account.getEmail());
                    ps.setString(6, account.getAddress());
                    ps.setString(7, account.getCity());
                    ps.setString(8, account.getState());
                    ps.setString(9, account.getZip());
                    return ps;
                }
            }, holder);

            // int newAccountId = holder.getKey().intValue();
            // account.setId(newAccountId);
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("Data object" + account + "not saved " + e);
        }
    }

    public void removeAccount(String id) {
        try {
            String deleteAccountSql = AccountsSqlConstants.removeAccountSql;
            jdbcTemplate.update(deleteAccountSql, id);
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("Data record could not be removed" + e);
        }
    }

    public void updateAccount(Accounts account){
        try {
            String updateAccountSql = AccountsSqlConstants.updateAccountSql;

            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(updateAccountSql);
                    ps.setString(1, account.getFirst_name());
                    ps.setString(2, account.getLast_name());
                    ps.setString(3, account.getMobile());
                    ps.setString(4, account.getHome());
                    ps.setString(5, account.getEmail());
                    ps.setString(6, account.getAddress());
                    ps.setString(7, account.getCity());
                    ps.setString(8, account.getState());
                    ps.setString(9, account.getZip());
                    ps.setInt(10, account.getId());
                    return ps;
                }
            });
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("Data record could not be updated" + e);
        }
    }


}
