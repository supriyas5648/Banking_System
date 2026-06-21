package dao;

import db.DBConnection;
import models.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public boolean createAccount(Account account) {

        String query =
                "INSERT INTO accounts " +
                "(account_no, name, account_type, balance) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(query)) {

            ps.setLong(
                    1,
                    account.getAccountNo()
            );

            ps.setString(
                    2,
                    account.getAccountHolderName()
            );

            ps.setString(
                    3,account.getAccountType()
                    
            );

            ps.setDouble(
                    4,account.getBalance()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public Account findAccount(
            long accountNo) {

        String query =
                "SELECT * FROM accounts " +
                "WHERE account_no = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(query)) {

            ps.setLong(1, accountNo);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return new Account(
                        rs.getLong("account_no"),
                        rs.getString(
                                "account_holder_name"
                        ),
                        rs.getDouble("balance"),
                        rs.getString(
                                "account_type"
                        )
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    public boolean updateBalance(
            long accountNo,
            double newBalance) {

        String query =
                "UPDATE accounts " +
                "SET balance=? " +
                "WHERE account_no=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(query)) {

            ps.setDouble(1, newBalance);
            ps.setLong(2, accountNo);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteAccount(
            long accountNo) {

        String query =
                "DELETE FROM accounts " +
                "WHERE account_no=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(query)) {

            ps.setLong(1, accountNo);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public List<Account> getAllAccounts() {

        List<Account> accounts =
                new ArrayList<>();

        String query =
                "SELECT * FROM accounts";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(query);
             ResultSet rs =
                     ps.executeQuery()) {

            while (rs.next()) {

                accounts.add(
                        new Account(
                                rs.getLong(
                                        "account_no"
                                ),
                                rs.getString(
                                        "account_holder_name"
                                ),
                                rs.getDouble(
                                        "balance"
                                ),
                                rs.getString(
                                        "account_type"
                                )
                        )
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return accounts;
    }
}