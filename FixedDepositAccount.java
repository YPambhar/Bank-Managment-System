package account;

public class FixedDepositAccount extends Account 
{
    public FixedDepositAccount(int accountNumber, String name, long balance) 
    {
        super(accountNumber, name, balance);
    }
    public boolean withdraw(long amount) 
    {
        System.out.println("Withdrawals are not permitted for Fixed Deposit Accounts before maturity.");
        return false;
    }
}
