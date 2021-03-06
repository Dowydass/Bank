import com.mysql.cj.jdbc.result.ResultSetImpl;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.awt.*;
import java.sql.*;

public class TransactionDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/bank";
    private static final String PASS = "";
    private static final String USER = "root";

    public static void createTableTransaction() {
        String query = "CREATE TABLE IF NOT EXISTS Transaction(" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "from_account_id INT," +
                "to_account_id INT," +
                "amount DOUBLE)";

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            System.out.println("Creating table Transaction.........");
            preparedStatement.executeUpdate(query);
            System.out.println("Table Transaction successfully created.......");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newTransaction(Transactions transactions) {
        String query = "INSERT INTO Transaction(from_account_id,to_account_id,amount) VALUES(?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, transactions.getFromAccount());
            preparedStatement.setInt(2, transactions.getToAccount());
            preparedStatement.setDouble(3, transactions.getAmount());
            System.out.println("New transaction was added...");
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void transaction(int fromAccount, int toAccount, double amount) {

        String checkBalanceFromAccount = "SELECT balance FROM Accounts WHERE id = '" + fromAccount + "'";

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(checkBalanceFromAccount);
            ResultSet resultSet = preparedStatement.executeQuery();

           if(fromAccount == toAccount){

               System.out.println(" ");
               System.out.println("Trying transfer amount to yourself");
               System.out.println("ABORTING");

               return;
           }
            if (resultSet.next()) {
                System.out.println(" ");
                System.out.println("User id: " + fromAccount + " Balance is: " + resultSet.getString(1));
                double amount2 = resultSet.getDouble(1);
                if (amount <= amount2) {            //Checks if amount in user account are bigger or equals then amount2



                    String selectBalanceToAccount = "SELECT balance FROM Accounts WHERE id = '" + toAccount + "'";
                    preparedStatement.executeQuery();
                    String query4 = "SELECT id FROM Accounts WHERE id = '" + toAccount + "'";
                    ResultSet resultSet2 = preparedStatement.executeQuery(query4);

                    if (resultSet2.next()) {

                        int checkIfExists = 0;
                        checkIfExists = resultSet2.getInt(1);

                        if (checkIfExists == toAccount) {
                            System.out.println("Account status: EXISTS");

                            ResultSet checkBalanceToAccount = preparedStatement.executeQuery(selectBalanceToAccount);

                            if (checkBalanceToAccount.next()) {

                                double balanceToAccountDouble = checkBalanceToAccount.getDouble(1);
                                double amountUpdatedToAccount = amount2 - amount;
                                double amountUpdatedFromAccount = balanceToAccountDouble + amount;
                                Statement s = connection.createStatement();
                                String s2 = "UPDATE Accounts SET balance  = '" + amountUpdatedToAccount + "' WHERE id = '" + toAccount + "'";


                                System.out.println(" ");
                                System.out.println("Sending amount: " + amount + " to id: " + toAccount);
                                System.out.println("New user: " + fromAccount + " balance: " + amountUpdatedFromAccount);

                                String s1 = "UPDATE Accounts SET balance  = '" + amountUpdatedFromAccount + "' WHERE id = '" + fromAccount + "'";
                                s.addBatch(s1);
                                s.addBatch(s2);
                                s.executeBatch();


                            }
                            Transactions transactions = new Transactions(fromAccount, toAccount, amount);
                            newTransaction(transactions);
                        } else {
                            System.out.println("Account doesn't exists rollback boys");
                        }
                    }
                } else {
                    System.out.println("Wrong amount - we going back boys");
                    System.err.println("rollback");
                }
            } else {
                System.err.println("There's are no records to display!");
            }

        } catch (SQLException e) {
            System.out.println("error occurred more information: " + e.getMessage());
            e.printStackTrace();
        }
    }

    }


