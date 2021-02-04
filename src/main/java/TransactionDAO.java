import com.mysql.cj.x.protobuf.MysqlxDatatypes;

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


    public static void checkBalance(int id, double amount) {


        String query = "SELECT balance FROM Accounts WHERE id = '" + id + "'";


        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                double amount2 = resultSet.getDouble(1);
                if (amount <= amount2) {
                    double amount3 = amount2 - amount;


                    System.out.println(amount3);
                  //String query1 = "UPDATE Accounts SET balance  = '" + amount3 + "' WHERE id = '" + id + "'";
                   // String query1 = "SELECT balance FROM Accounts WHERE id = '" + 2 + "'";


                    //UPDATE accounts SET balance = 900 WHERE id = 1
                    //Palei viska ties cia pasidaviau
                    //
                    //
                    //
                    //
                    // preparedStatement.executeQuery(query1);

                } else {
                    System.out.println("Wrong amount");
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

