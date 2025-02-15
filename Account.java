package account;
import java.io.*;

public abstract class Account
{
    private int accountNumber;
    private String name;
    protected long balance;

    public Account(int accountNumber, String name, long balance) 
    {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public int getAccountNumber() 
    {
         return accountNumber; 
    }
    public String getName() 
    { 
        return name;
     }
    public long getBalance() 
    {
         return balance;
     }
    public void deposit(long amount) 
    {
        balance =balance+ amount;
     }
    public abstract boolean withdraw(long amount);

    public void saveAccount() throws IOException 
    {
        BufferedWriter a = new BufferedWriter(new FileWriter("accounts.txt", true));
        a.write(accountNumber + " " + name + " " + balance + " " + getClass().getSimpleName());
        a.newLine();
        a.close();
    }

    public static Account findAccount(int accountNumber) throws IOException 
    {
        BufferedReader r = new BufferedReader(new FileReader("accounts.txt"));
        String line;
        while ((line = r.readLine()) != null) {
            String[] data = line.trim().split(" ");
            if (data.length == 4) 
            {
                int accNum = Integer.parseInt(data[0]);
                String accName = data[1];
                long accBalance = Long.parseLong(data[2]);
                String accountType = data[3];
                if (accNum == accountNumber) 
                {
                    r.close();
                    if ("SavingsAccount".equals(accountType))
                    {
                        return new SavingsAccount(accNum, accName, accBalance);
                    } 
                    else if ("FixedDepositAccount".equals(accountType)) 
                    {
                        return new FixedDepositAccount(accNum, accName, accBalance);
                    }
                }
            }
        }
        r.close();
        return null;
    }

    public static void searchAccountByName(String searchName) throws IOException 
    {
        BufferedReader r = new BufferedReader(new FileReader("accounts.txt"));
        String line;
        boolean found = false;
        while ((line = r.readLine()) != null) 
        {
            String[] data = line.trim().split(" ");
            if (data.length == 4) 
            {
                String accName = data[1];
                if (accName.equalsIgnoreCase(searchName)) 
                {
                    int accNum = Integer.parseInt(data[0]);
                    long accBalance = Long.parseLong(data[2]);
                    String accountType = data[3];
                    Account account;
                    if ("SavingsAccount".equals(accountType)) 
                    {
                        account = new SavingsAccount(accNum, accName, accBalance);
                    } 
                    else 
                    {
                        account = new FixedDepositAccount(accNum, accName, accBalance);
                    }
                    System.out.println("\n" + account);
                    found = true;
                }
            }
        }
        r.close();
        if (!found) 
        {
            System.out.println("No account found with the name: " + searchName);
        }
    }

    public void updateAccount() throws IOException 
    {
        File inputFile = new File("accounts.txt");
        File tempFile = new File("temp.txt");

        BufferedReader r = new BufferedReader(new FileReader(inputFile));
        BufferedWriter w = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while ((line = r.readLine()) != null) 
        {
            String[] data = line.trim().split(" ");
            if (data.length == 4) {
                int accNum = Integer.parseInt(data[0]);
                if (accNum == accountNumber) 
                {
                    w.write(accountNumber + " " + name + " " + balance + " " + getClass().getSimpleName());
                } 
                else
                {
                    w.write(line);
                }
                w.newLine();
            }
        }

        w.close();
        r.close();

        inputFile.delete();
        tempFile.renameTo(inputFile);
    }
    
    public String toString() 
    {
        return "Account Number: " + accountNumber + "\nName: " + name + "\nBalance: " + balance;
    }
    public static void suggestAccountType(long minBalance, long maxBalance,int time) 
    {
        System.out.println("\nSuggested Account Types:");
        if (minBalance >= 1000 && maxBalance <= 50000 && time<=2) 
        {
            System.out.println("Savings Account: Suitable for balances between Rs. 1,000 and Rs. 50,000.");
        }
        if (minBalance > 50000 && maxBalance <= 500000 && time<=4) 
        {
            System.out.println("Fixed Deposit Account: Suitable for balances between Rs. 50,001 and Rs. 500,000.");
        }
        if (maxBalance > 500000 && time<=6) 
        {
            System.out.println("High-Value Account: Suitable for balances above Rs. 500,000.");
        }
        if (minBalance < 1000 && time<=8) 
        {
            System.out.println(" Minimum balance required for a Savings Account is Rs. 1,000.");
        }
    }
}
