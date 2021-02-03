import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDAO {

private static final String URL = "jdbc:mysql://localhost:3306/bank";
private static final String PASS = "";
private static final String USER = "root";

    public static void createTableAccount(){
        String query =  "CREATE TABLE IF NOT EXISTS Accounts(" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "first_name VARCHAR(255)," +
                "last_name VARCHAR(255)," +
                "balance DOUBLE)";

            try{
                Connection connection = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Creating table Acounts.........");
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate(query);
                System.out.println("Table accounts was successfully created......");


            }catch (SQLException e){
                e.printStackTrace();
            }
    }
    public static void createAccount(Account account){
            String query = "INSERT INTO Accounts(first_name,last_name,balance) VALUES(?,?,?)";
            try{
                Connection connection = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, account.getFirstName());
                preparedStatement.setString(2, account.getLastName());
                preparedStatement.setDouble(3, account.getBalance());
                System.out.println("New account was added...");
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
    }

}
