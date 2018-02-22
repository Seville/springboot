package fashion.hats.constants;

public class AccountsSqlConstants {

    public static String getAllAccountsSql = "SELECT * FROM accounts";

    public static String getAccountsByFirstNameSql = "SELECT * FROM `accounts` WHERE first_name = ?";

    public static String createAccountSql = "INSERT INTO `accounts` (first_name, last_name, mobile, home, email, address, city, state, zip) values (?,?,?,?,?,?,?,?,?)";

    public static String removeAccountSql = "DELETE FROM `accounts` WHERE id = ?";

    public static String updateAccountSql = "UPDATE `accounts`" +
            "SET first_name = ?, " +
            "last_name = ?, " +
            "mobile = ?, " +
            "home = ?, " +
            "email = ?, " +
            "address = ?, " +
            "city = ?, " +
            "state = ?, " +
            "zip = ? " +
            "WHERE id = ?";
}
