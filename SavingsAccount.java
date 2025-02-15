package account;

public class SavingsAccount extends Account 
{
    public SavingsAccount(int accountNumber, String name, long balance)
    {
        super(accountNumber, name, balance);
    }
    public boolean withdraw(long amount) 
    {
        if (amount <= balance) 
        {
            balance -= amount;
            return true;
        }
        return false;
    }
}
