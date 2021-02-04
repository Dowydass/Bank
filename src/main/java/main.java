public class main {
    public static void main(String[] args){


          //  Account account = new Account("Petras","Petddasdas",900);
          //  AccountDAO.createAccount(account);
                AccountDAO.createTableAccount();
        TransactionDAO.createTableTransaction();
                Transactions transactions = new Transactions(1,2,100);

                TransactionDAO.checkBalance(transactions.getFromAccount(),transactions.getAmount()); //Checking user balance
                TransactionDAO.newTransaction(transactions);




    }
}
