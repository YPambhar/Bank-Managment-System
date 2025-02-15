import account.Account;
import account.FixedDepositAccount;
import account.SavingsAccount;
import java.io.IOException;
import java.util.Scanner;

public class Bank 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            System.out.println("\nBank Management System");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Display Account Information");
            System.out.println("5. Search Account by Name");
            System.out.println("6. Investment Guide");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int ch = sc.nextInt();

            try
            {
                switch (ch) 
                {
                    case 1:
                        System.out.print("Enter Account Type (1 for Savings, 2 for Fixed Deposit): ");
                        int type = sc.nextInt();
                        System.out.print("Enter Account Number: ");
                        int accountNumber = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Initial Balance: ");
                        long balance = sc.nextLong();

                        Account account;
                        if (type == 1) 
                        {
                            account = new SavingsAccount(accountNumber, name, balance);
                        } 
                        else 
                        {
                            account = new FixedDepositAccount(accountNumber, name, balance);
                        }
                        account.saveAccount();
                        System.out.println("Account created successfully!");
                        break;

                    case 2:
                        System.out.print("Enter Account Number: ");
                        int depositAccNumber = sc.nextInt();
                        Account depositAcc = Account.findAccount(depositAccNumber);
                        if (depositAcc != null) 
                        {
                            System.out.print("Enter Deposit Amount: ");
                            long depositAmount = sc.nextLong();
                            if(depositAmount<0)
                            {
                                System.out.println("Eneter proper deposite amount..");
                                break;
                            }
                            else
                            {
                                depositAcc.deposit(depositAmount);
                                depositAcc.updateAccount();
                                System.out.println("Amount deposited successfully!");
                            }
                           
                        } 
                        else 
                        {
                            System.out.println("Account not found!");
                        }
                        break;

                    case 3:
                        System.out.print("Enter Account Number: ");
                        int withdrawAccNumber = sc.nextInt();
                        Account withdrawAcc = Account.findAccount(withdrawAccNumber);
                        if (withdrawAcc != null) 
                        {
                            System.out.print("Enter Withdrawal Amount: ");
                            long withdrawalAmount = sc.nextLong();
                            if(withdrawalAmount<0)
                            {
                                System.out.println("Enter proper withdrawalamount...");
                                break;
                            }
                            else
                            {
                                if (withdrawAcc.withdraw(withdrawalAmount)) 
                                {
                                    withdrawAcc.updateAccount();
                                    System.out.println("Amount withdrawn successfully!");
                                } else {
                                    System.out.println("Insufficient balance!");
                                }                                
                            }
                        } 
                        else 
                        {
                            System.out.println("Account not found!");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Account Number: ");
                        int displayAccNumber = sc.nextInt();
                        Account displayAcc = Account.findAccount(displayAccNumber);
                        if (displayAcc != null) 
                        {
                            System.out.println("\nAccount Information:\n" + displayAcc);
                        }
                        else 
                        {
                            System.out.println("Account not found!");
                        }
                        break;

                    case 5:
                        System.out.print("Enter Account Holder's name: ");
                        sc.nextLine();
                        String searchName = sc.nextLine();
                        Account.searchAccountByName(searchName);
                        break;

                    case 6:
                        System.out.print("Enter the minimum balance for counseling: ");
                        long min = sc.nextLong();
                        System.out.print("Enter the maximum balance for counseling: ");
                        long max = sc.nextLong();
                        System.out.print("Enter the time period for counseling: ");
                        int time = sc.nextInt();
                        Account.suggestAccountType(min, max,time);
                        break;

                    case 7:
                        System.out.println("Exiting system.");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } 
            catch (IOException e) 
            {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
