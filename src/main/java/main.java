

public class main {
    public static void main(String[] args){


          //  Account account = new Account("Petras","Petddasdas",900);
          //  AccountDAO.createAccount(account);
                AccountDAO.createTableAccount();
                TransactionDAO.createTableTransaction();


                TransactionDAO.transaction(1,8,100); //Checking user balance

    }
}
