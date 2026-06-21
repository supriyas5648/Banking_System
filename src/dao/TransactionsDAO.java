package dao;

import db.DBConnection;
import models.Transactions;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionsDAO {

    public boolean saveTransaction(
            Transactions transaction) {

        String query =
                "INSERT INTO transactions " +
                "(account_no, transaction_type, amount, transaction_time) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con =
                     DBConnection.getConnection();

             PreparedStatement ps =
                     con.prepareStatement(query)) {

            ps.setLong(
                    1,
                    transaction.getAccountNo()
            );

            ps.setString(
                    2,
                    transaction.getTransactionType()
            );

            ps.setDouble(
                    3,
                    transaction.getAmount()
            );

            ps.setTimestamp(
                    4,
                    Timestamp.valueOf(
                            transaction.getTransactionTime()
                    )
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public List<Transactions> getTransactionsByAccount(
            long accountNo) {

        List<Transactions> transactions =
                new ArrayList<>();

        String query =
                "SELECT * FROM transactions " +
                "WHERE account_no=? " +
                "ORDER BY transaction_time DESC";

        try (Connection con =
                     DBConnection.getConnection();

             PreparedStatement ps =
                     con.prepareStatement(query)) {

            ps.setLong(1, accountNo);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                transactions.add(
                        new Transactions(
                                rs.getInt(
                                        "transaction_id"
                                ),
                                rs.getLong(
                                        "account_no"
                                ),
                                rs.getString(
                                        "transaction_type"
                                ),
                                rs.getDouble(
                                        "amount"
                                ),
                                rs.getTimestamp(
                                        "transaction_time"
                                ).toLocalDateTime()
                        )
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return transactions;
    }

    public List<Transactions> getAllTransactions() {

        List<Transactions> transactions =
                new ArrayList<>();

        String query =
                "SELECT * FROM transactions " +
                "ORDER BY transaction_time DESC";

        try (Connection con =
                     DBConnection.getConnection();

             PreparedStatement ps =
                     con.prepareStatement(query);

             ResultSet rs =
                     ps.executeQuery()) {

            while (rs.next()) {

                transactions.add(
                        new Transactions(
                                rs.getInt(
                                        "transaction_id"
                                ),
                                rs.getLong(
                                        "account_no"
                                ),
                                rs.getString(
                                        "transaction_type"
                                ),
                                rs.getDouble(
                                        "amount"
                                ),
                                rs.getTimestamp(
                                        "transaction_time"
                                ).toLocalDateTime()
                        )
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return transactions;
    }
}