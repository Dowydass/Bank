import java.sql.*;

public class TransactionDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/bank";
    private static final String PASS = "";
    private static final String USER = "root";

    public static void createTableTransaction(){
        String query = "CREATE TABLE IF NOT EXISTS Transaction(" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "from_account_id VARCHAR(255)," +
                "to_account_id VARCHAR (255)," +
                "amount DOUBLE)";

        try{
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            System.out.println("Creating table Transaction.........");
            preparedStatement.executeUpdate(query);
            System.out.println("Table Transaction successfully created.......");


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void checkBalance(int id){


        String query = "SELECT * FROM Accounts WHERE id = '" + id +"'";

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                do {
                    System.out.println(resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3) + "," + resultSet.getString(4));
                } while (resultSet.next());

            } else {
                System.err.println("There's are no records to display!");
            }

        } catch (SQLException e) {
            System.out.println("error occurred more information: " + e.getMessage());
        }
    }
}

