

public class main {
    public static void main(String[] args) throws InterruptedException {


          //  Account account = new Account("Petras","Petddasdas",900);
          //  AccountDAO.createAccount(account);



                AccountDAO.createTableAccount();
                TransactionDAO.createTableTransaction();

                TransactionDAO.transaction(1,2,300); //Checking user balance
    }
    }



