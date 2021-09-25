package com.company;
import javax.management.monitor.StringMonitor;
import java.awt.*;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.lang.String;
import java.util.Random;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import static java.lang.Integer.*;


//Customer Class
class Customer
{
    protected
    String CNIC, Name, Address,Phone_No;
    int Account_Number;


    Customer()
    {
        CNIC = null;
        Name = null;
        Address = null;
        Account_Number = 0;
        Phone_No = null;

    }

    Customer(String CNIC, String Name, String Address, int Account_Number, String Phone_No)
    {
        this.CNIC = CNIC;
        this.Name = Name;
        this.Address = Address;
        this.Account_Number = Account_Number;
        this.Phone_No = Phone_No;

    }


}

//Saving Account
class SavingAccount extends Customer
{
    protected
    double Current_Balance;
    String Trans_Date_Time;
    String  Pin_Code;

    SavingAccount()
    {
        this.Current_Balance = 0;
        this.Trans_Date_Time = null;
    }


    SavingAccount(String CNIC, String Name, String Address, int Acc_No, String Phon_No, String D_T, String Pin, double Balnce)
    {
        super(CNIC, Name, Address, Acc_No, Phon_No);
        this.Trans_Date_Time = D_T;
        this.Pin_Code = Pin;
        this.Current_Balance = Balnce;

    }




    //Function to Validtae the account limit
    boolean ValidateAccount(String CNIC)
    {
        this.CNIC = CNIC;
        File myfile = new File("SavingAccount_UserCNIC.txt");
        try
        {
            Scanner sc = new Scanner(myfile);
            while (sc.hasNextLine())
            {
                String CNIC1 = sc.nextLine();
                if(Objects.equals(this.CNIC, CNIC1))
                {
                    return true;
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not read.");
            e.printStackTrace();
        }
        return false;

    }

    //Function To verify already existing account
    void verifyAlreadyExistingAccount(int Acc_No, String Pin)
    {
        this.Account_Number = Acc_No;
        this.Pin_Code = Pin;

        File myfile = new File("SavingAccount_UserAccountNumber.txt");
        File myfile1 = new File("SavingAccount_AccountPinCode.txt");


        try {

            Scanner s1 = new Scanner(myfile);
            Scanner s2 = new Scanner(myfile1);

            while (s1.hasNextLine()) {
                String d = s1.nextLine();

                Main.x1++;
                System.out.println(d);

                if (d.isEmpty()) {
                    break;
                }
                if (valueOf(d) == Account_Number) {
                    Main.verifier1 = 1;
                    break;
                }


            }
            s1.close();


            while (s2.hasNextLine()) {
                String d = s2.nextLine();

                Main.x2++;
                System.out.println(d);


                if (Objects.equals(d, Pin_Code) && Main.x2 == Main.x1) {

                    Main.verifier2 = 1;
                    break;
                }


            }
            s2.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not read.");
            e.printStackTrace();
        }
        System.out.println(Main.verifier1 + " verifier " + Main.verifier2);
        System.out.println(Main.x1 + " " + Main.x2);
    }


    //Function to display Name and Balance
    void checkS_Balance()
    {
        System.out.println("Your S_Name is : " + this.Name);
        System.out.println("Your Account S_Balance is : " + this.Current_Balance);
    }

    void printStatement()
    {
        System.out.println("Your complete detail is : ");
        System.out.println("    S_Name : " + this.Name);
        System.out.println("    Account Number : " + this.Account_Number);
        System.out.println("    Phone Number : " + this.Phone_No);
        System.out.println("    S_Address : " + this.Address);
        System.out.println("    Current Balance : " + this.Current_Balance);
        //System.out.println("    Transaction Date : " /*+ date*/

        //System.out.println("    Transaction Time : " + time);
        //System.out.println("    Remaining S_Balance is : " //+ reamining S_Balance


    }

    void makeDeposit(double D_Amount, char accountType)
    {
        int line = 0;
        String FileName = "SavingAccount_AccountBalance.txt";
        File myfile = new File(FileName);
        String data = null;
        try
        {
            Scanner sc = new Scanner(myfile);
            while (sc.hasNextLine())
            {
                line++;
                data = sc.nextLine();
                //System.out.println(data);

                if( (line == Main.x1)  &&  (line == Main.x2) && (accountType == 'A' || accountType == 'a') )
                {
                    double data1 = Double.valueOf(data);

                    data1 = data1 + D_Amount;
                    Main.setVariable(line, String.valueOf(data1), FileName);
                    this.Current_Balance = data1;
                }
            }
            if(accountType == 'N' || accountType == 'n')
            {


                System.out.println("Last Line : " + line);
                System.out.println(data);

                double data1 = Double.valueOf(data);
                data1 = data1 + D_Amount;

                Main.setVariable(line, String.valueOf(data1), FileName);
                this.Current_Balance = data1;


            }

            sc.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not read.");
            e.printStackTrace();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }



    void makeWithdrawl(double Withdraw_Amount, char accountType)
    {
        int line = 0;
        String FileName = "SavingAccount_AccountBalance.txt";
        File myfile = new File(FileName);
        String data = null;
        try
        {
            Scanner sc = new Scanner(myfile);
            while (sc.hasNextLine())
            {
                line++;
                data = sc.nextLine();
                //System.out.println(data);

                if( (line == Main.x1)  &&  (line == Main.x2) && (accountType == 'A' || accountType == 'a') )
                {
                    if(data !=  null) {
                        double data1 = Double.valueOf(data);

                        data1 = data1 - Withdraw_Amount;
                        Main.setVariable(line, String.valueOf(data1), FileName);
                        this.Current_Balance = data1;
                    }
                }
            }
            if(accountType == 'N' || accountType == 'n')
            {


                System.out.println("Last Line : " + line);
                System.out.println(data);
                if(data !=  null) {
                    double data1 = Double.valueOf(data);
                    data1 = data1 + Withdraw_Amount;

                    Main.setVariable(line, String.valueOf(data1), FileName);
                    this.Current_Balance = data1;
                }


            }

            sc.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not read.");
            e.printStackTrace();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void TransferAmount()
    {
        System.out.print("Enter your account number:");

    }
}

class CheckingAccount extends Customer
{
    protected
    double Current_Balance;
    String Trans_Date_Time;
    String Pin_Code;

    CheckingAccount()
    {
        this.Current_Balance = 0;
        this.Trans_Date_Time = null;
    }

    CheckingAccount(String CNIC, String Name, String Address, int Acc_No, String Phon_No, String D_T, String Pin, double Balnce)
    {
        super(CNIC, Name, Address, Acc_No, Phon_No);
        this.Trans_Date_Time = D_T;
        this.Pin_Code = Pin;
        this.Current_Balance = Balnce;
    }

    boolean ValidateAccount(String CNIC)
    {
        this.CNIC = CNIC;
        File myfile = new File("CheckingAccount_UserCNIC.txt");
        try
        {
            Scanner sc = new Scanner(myfile);
            while (sc.hasNextLine())
            {
                String CNIC1 = sc.nextLine();
                //System.out.println(CNINC);
                if(Objects.equals(this.CNIC, CNIC1))
                {
                    return true;
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not read.");
            e.printStackTrace();
        }
        return false;

    }


    //Function To verify already existing account
    void verifyAlreadyExistingAccount(int Acc_No, String Pin)
    {
        this.Account_Number = Acc_No;
        this.Pin_Code = Pin;

        File myfile = new File("CheckingAccount_UserAccountNumber.txt");
        File myfile1 = new File("CheckingAccount_AccountPinCode.txt");


        try {

            Scanner s1 = new Scanner(myfile);
            Scanner s2 = new Scanner(myfile1);

            while (s1.hasNextLine()) {
                String d = s1.nextLine();

                Main.x1++;
                System.out.println(d);

                if (d.isEmpty()) {
                    break;
                }
                if (valueOf(d) == Account_Number) {
                    Main.verifier1 = 1;
                    break;
                }


            }
            s1.close();


            while (s2.hasNextLine()) {
                String d = s2.nextLine();

                Main.x2++;
                System.out.println(d);


                if (Objects.equals(d, Pin_Code) && Main.x2 == Main.x1) {

                    Main.verifier2 = 1;
                    break;
                }


            }
            s2.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not read.");
            e.printStackTrace();
        }
        System.out.println(Main.verifier1 + " verifier " + Main.verifier2);
        System.out.println(Main.x1 + " " + Main.x2);
    }


    //Function to display all details
    void printStatement()
    {
        System.out.println("Your complete detail is : ");
        System.out.println("    S_Name : " + this.Name);
        System.out.println("    Account Number : " + this.Account_Number);
        System.out.println("    Phone Number : " + this.Phone_No);
        System.out.println("    S_Address : " + this.Address);
        System.out.println("    Current Balance : " + this.Current_Balance);
        //System.out.println("    Transaction Date : " /*+ date*/

        //System.out.println("    Transaction Time : " + time);
        //System.out.println("    Remaining S_Balance is : " //+ reamining S_Balance


    }

    //Function to display name and Balance
    void checkBalance()
    {
        System.out.println("Name is : " + this.Name);
        System.out.println("Balance is : "  + this.Current_Balance);
    }

    //Function to perform cash deposition
    void makeDeposit(double D_Amount, char accountType)
    {
        int line = 0;
        int LastLine =0;
        String FileName = "CheckingAccount_AccountBalance.txt";
        File myfile = new File(FileName);
        String data = null;
        try
        {
            Scanner sc = new Scanner(myfile);
            while (sc.hasNextLine())
            {
                line++;
                //LastLine++;
                data = sc.nextLine();
                //System.out.println(data);

                if(line == Main.x1  &&  line == Main.x2 && accountType == 'A' || accountType == 'a')
                {
                    if(data !=  null) {
                        double data1 = Double.valueOf(data);

                        data1 = data1 + D_Amount;
                        Main.setVariable(line, String.valueOf(data1), FileName);
                        this.Current_Balance = data1;
                    }
                }

            }

            if(accountType == 'N' || accountType == 'n')
            {
                if(data !=  null) {
                    double data1 = Double.valueOf(data);
                    data1 = data1 + D_Amount;

                    Main.setVariable(line, String.valueOf(data1), FileName);
                    this.Current_Balance = data1;
                }

            }

            sc.close();
            System.out.println("Successful.");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not read.");
            e.printStackTrace();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }



    //Function to perform cash Withdrawal
    void makeWithdrawl(double Withdraw_Amount, char accountType)
    {
        int line = 0;
        int LastLine =0;
        String FileName = "CheckingAccount_AccountBalance.txt";
        File myfile = new File(FileName);
        String data = null;
        try
        {
            Scanner sc = new Scanner(myfile);
            while (sc.hasNextLine())
            {
                line++;
                //LastLine++;
                data = sc.nextLine();
                //System.out.println(data);

                if(line == Main.x1  &&  line == Main.x2 && accountType == 'A' || accountType == 'a')
                {
                    if(data !=  null) {
                        double data1 = Double.valueOf(data);

                        data1 = data1 - Withdraw_Amount;
                        Main.setVariable(line, String.valueOf(data1), FileName);
                        this.Current_Balance = data1;
                    }
                }

            }
            if(accountType == 'N' || accountType == 'n')
            {
                if(data !=  null) {
                    double data1 = Double.valueOf(data);
                    data1 = data1 - Withdraw_Amount;

                    Main.setVariable(line, String.valueOf(data1), FileName);
                    this.Current_Balance = data1;
                }

            }

            sc.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not read.");
            e.printStackTrace();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}



public class Main {

    //shared variables among all classes
    public static int verifier1 = 0, verifier2 = 0, x1 = 0, x2 = 0;

    public static void setVariable(int lineNumber, String data, String FileName){
        Path path = Paths.get(FileName);
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error.");
        }
        lines.set(lineNumber - 1, data);
        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error.");
        }
    }



    public static void main(String[] args) {
        // write your code her

        char Account_choice, Account_Type = '\0';

        String S_new_Pin_Code = null, C_new_Pin_Code = null;
        double S_New_Balance = 0, C_New_Balance = 0;
        String S_new_CNIC = null, S_new_Name =  null, S_new_Phone_Number = null, S_new_Address = null,
                C_new_CNIC = null, C_new_Name = null, C_new_Phone_Number = null, C_new_Address = null;
        int S_new_Account_No = 0, C_new_Account_No = 0;
        String S_formattedDate = null, C_formattedDate = null;
        int choice, choice1 = 1;
        int lineCheck = 0;




        /*--------------------------------Arrays FOR SAVING ACCOUNTS-------------------------*/
        //Array of S_Names
        //String[] S_Name = new String[100];

        //Array of Phone Numbers
        //String[] S_Phone_Number = new String[100];

        //Array of Account Numbers
        //String[] S_Account_Number = new String[100];

        //Array of S_Addresses
        //String S_Address[] = new String[100];

        //Array of S_Balance
        //double S_Balance[] = new double[100];


        /*--------------------------------Arrays FOR CHECKING ACCOUNTS-------------------------*/
        //Array of S_Names
        //String[] C_Name = new String[100];

        //Array of Phone Numbers
        //String[] C_Phone_Number = new String[100];

        //Array of Account Numbers
        //String[] C_Account_Number = new String[100];

        //Array of S_Addresses
        //String C_Address[] = new String[100];

        //Array of S_Balance
        //double C_Balance[] = new double[100];


        //--------------------------------------------------------------------------------------------------------------------------------------------------------------


        Random random = new Random();
        Customer C = new Customer();
        SavingAccount SA = new SavingAccount();
        CheckingAccount CA = new CheckingAccount();
        int validation = -1;
        //CheckingAccount CA = new CheckingAccount();

        do {
            do {


                System.out.print("\n\t\t\t\t\t\tWelcome To Account Management System\n");
                System.out.println("\t\t\t\t\t--------------------------------------------\n");
                System.out.print(" Do you Already Have an Account or You want to Make a New Account?\n Press A/a for Already & N/n for New Account: ");
                Scanner sc = new Scanner(System.in);
                Account_choice = sc.nextLine().charAt(0);

                //

                System.out.println("------------------------------\n");


                //IN CASE OF NEW ACCOUNT

                int a = 10;
                if (Account_choice == 'N' || Account_choice == 'n') {
                    System.out.print(" Do you want to create a Saving  Account or a Checking Account? \n Press S/s for Saving & C/c for Checking: ");
                    Account_Type = sc.nextLine().charAt(0);


                    if (Account_Type == 'S' || Account_Type == 's')                    //Saving Account
                    {


                        //Files Creation for New & Old Savings Account
                        try {
                            File S0 = new File("SavingAccount_UserCNIC.txt");
                            File S1 = new File("SavingAccount_UserNames.txt");
                            File S2 = new File("SavingAccount_UserPhoneNo.txt");
                            File S3 = new File("SavingAccount_UserAccountNumber.txt");
                            File S4 = new File("SavingAccount_UserAddress.txt");
                            File S5 = new File("SavingAccount_AccountPinCode.txt");
                            File S6 = new File("SavingAccount_AccountBalance.txt");
                            File S7 = new File("SavingAccount_AccountCreationDate&Time.txt");
                            if (S0.createNewFile()) {
                            }
                        } catch (IOException e) {
                            System.out.println("An error occurred.File not successfully Created.");
                            e.printStackTrace();
                        }

                        System.out.print(" Enter Your CNIC Number (e.g 12121-7811004-5) : ");
                        S_new_CNIC = sc.nextLine();

                        if ((SA.ValidateAccount(S_new_CNIC))) {
                            System.out.println("Sorry you cannot proceed furthur.\nAn account to this CNIC is already registered. You can have only one Saving Account at a time.");
                            validation = 1;
                            break;

                        } else if (!(SA.ValidateAccount(S_new_CNIC))) {
                            System.out.println("You can Proceed Furthur.");
                            validation = 0;


                        }


                        System.out.print(" Enter Your Name:");
                        S_new_Name = sc.nextLine();


                        System.out.print(" Enter Your Phone Number:");
                        S_new_Phone_Number = sc.nextLine();


                        System.out.print(" Enter Your Address (e.g House#1, Street#1, Lahore) : ");
                        S_new_Address = sc.nextLine();


                        S_new_Account_No = 700000 + random.nextInt(100000);
                        System.out.print(" Please note, your Account Number is:");
                        System.out.println(S_new_Account_No);


                        System.out.print("\n\n Enter a 7-digit Pin Code of your choice for the New Account:");
                        S_new_Pin_Code = sc.nextLine();


                        System.out.print(" Enter the S_Balance for your new account.It is mandatory to enter:");
                        S_New_Balance = sc.nextDouble();

                        //Time & Date
                        LocalDateTime myDateObj = LocalDateTime.now();
                        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        S_formattedDate = myDateObj.format(myFormatObj);        //final date and time
                        //System.out.println(formattedDate);


                    /*
                           Writing all information about the new Saving Account to the Files
                    */

                        //Writing to files.
                        try {
                            FileWriter W0 = new FileWriter("SavingAccount_UserCNIC.txt", true);
                            W0.write(S_new_CNIC + "\n");
                            W0.close();

                            FileWriter W1 = new FileWriter("SavingAccount_UserNames.txt", true);
                            W1.write(S_new_Name + "\n");
                            W1.close();

                            FileWriter W2 = new FileWriter("SavingAccount_UserPhoneNo.txt", true);
                            W2.write(S_new_Phone_Number + "\n");
                            W2.close();

                            FileWriter W3 = new FileWriter("SavingAccount_UserAddress.txt", true);
                            W3.write(S_new_Address + "\n");
                            W3.close();

                            FileWriter W4 = new FileWriter("SavingAccount_UserAccountNumber.txt", true);
                            W4.write(S_new_Account_No + "\n");
                            W4.close();

                            FileWriter W5 = new FileWriter("SavingAccount_AccountPinCode.txt", true);
                            W5.write(S_new_Pin_Code + "\n");
                            W5.close();

                            FileWriter W6 = new FileWriter("SavingAccount_AccountBalance.txt", true);
                            W6.write(S_New_Balance + "\n");
                            W6.close();

                            FileWriter W7 = new FileWriter("SavingAccount_AccountCreationDate&Time.txt", true);
                            W7.write(S_formattedDate + "\n");
                            W7.close();


                        } catch (IOException e) {
                            System.out.println("An error occurred. Writing to Saving Files did not completed.");
                            e.printStackTrace();
                        }


                        //Passing Arguments to Constructor
                        SA = new SavingAccount(S_new_CNIC, S_new_Name, S_new_Address, S_new_Account_No, S_new_Phone_Number, S_formattedDate, S_new_Pin_Code, S_New_Balance);


                    } else if (Account_Type == 'C' || Account_Type == 'c')                //Checking Account
                    {


                        //Files Creation for New & Old Checking Account
                        try {
                            File C0 = new File("CheckingAccount_UserCNIC.txt");
                            File C1 = new File("CheckingAccount_UserNames.txt");
                            File C2 = new File("CheckingAccount_UserPhoneNo.txt");
                            File C3 = new File("CheckingAccount_UserAccountNumber.txt");
                            File C4 = new File("CheckingAccount_UserAddress.txt");
                            File C5 = new File("CheckingAccount_AccountPinCode.txt");
                            File C6 = new File("CheckingAccount_AccountBalance.txt");
                            File C7 = new File("CheckingAccount_AccountCreationDate&Time.txt");
                            if (C0.createNewFile()) {
                            }
                        } catch (IOException e) {
                            System.out.println("An error occurred.File not successfully Created.");
                            e.printStackTrace();
                        }

                        System.out.print(" Enter Your CNIC Number (e.g 12121-7811004-5) : ");
                        C_new_CNIC = sc.nextLine();

                        if (SA.ValidateAccount(C_new_CNIC)) {

                            System.out.println("Sorry you cannot proceed furthur.\nAn account to this CNIC is aleady registered. You can have only one Checking Account at a time.");
                            validation = 1;
                            break;
                        } else if (!(SA.ValidateAccount(C_new_CNIC))) {
                            System.out.println("You can Proceed Furthur.");
                            validation = 0;
                        }


                        System.out.print(" Enter Your Name:");
                        C_new_Name = sc.nextLine();


                        System.out.print(" Enter Your Phone Number:");
                        C_new_Phone_Number = sc.nextLine();

                        System.out.print(" Enter Your Address (e.g House#1, Street#1, Lahore) : ");
                        C_new_Address = sc.nextLine();

                        C_new_Account_No = 700000 + random.nextInt(100000);
                        System.out.print(" Please note, your Account Number is:");
                        System.out.println(C_new_Account_No);

                        //Time & Date
                        LocalDateTime myDateObj = LocalDateTime.now();
                        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        C_formattedDate = myDateObj.format(myFormatObj);        //final date and time


                        System.out.print("\n\n Enter a 7-digit Pin Code of your choice for the New Account:");
                        C_new_Pin_Code = sc.nextLine();

                        System.out.print(" Enter the Balance for your new account.It is mandatory to enter:");
                        C_New_Balance = sc.nextDouble();

                    /*
                           Writing all information about the new Checking Account to the Files
                    */

                        //Writing to files.
                        try {
                            FileWriter B0 = new FileWriter("CheckingAccount_UserCNIC.txt", true);
                            B0.write(C_new_CNIC + "\n");
                            B0.close();

                            FileWriter B1 = new FileWriter("CheckingAccount_UserNames.txt", true);
                            B1.write(C_new_Name + "\n");
                            B1.close();

                            FileWriter B2 = new FileWriter("CheckingAccount_UserPhoneNo.txt", true);
                            B2.write(C_new_Phone_Number + "\n");
                            B2.close();

                            FileWriter B3 = new FileWriter("CheckingAccount_UserAddress.txt", true);
                            B3.write(C_new_Address + "\n");
                            B3.close();

                            FileWriter B4 = new FileWriter("CheckingAccount_UserAccountNumber.txt", true);
                            B4.write(C_new_Account_No + "\n");
                            B4.close();

                            FileWriter B5 = new FileWriter("CheckingAccount_AccountPinCode.txt", true);
                            B5.write(C_new_Pin_Code + "\n");
                            B5.close();

                            FileWriter B6 = new FileWriter("CheckingAccount_AccountBalance.txt", true);
                            B6.write(C_New_Balance + "\n");
                            B6.close();

                            FileWriter B7 = new FileWriter("CheckingAccount_AccountCreationDate&Time.txt", true);
                            B7.write(C_formattedDate + "\n");
                            B7.close();


                        } catch (IOException e) {
                            System.out.println("An error occurred. Writing To Checking Files did not completed.");
                            e.printStackTrace();
                        }

                        //Passing Arguments to Constructor
                        CA = new CheckingAccount(C_new_CNIC, C_new_Name, C_new_Address, C_new_Account_No, C_new_Phone_Number, C_formattedDate, C_new_Pin_Code,C_New_Balance);


                    }
                    System.out.println("Congrats! You account has been created.");
                } else if (Account_choice == 'A' || Account_choice == 'a') {

                    System.out.println("Which type of Account you have? (Saving/Checking)");
                    System.out.println("Enter S/s for Saving Account & C/c for Checking Account : ");
                    Account_Type = sc.nextLine().charAt(0);

                    if (Account_Type == 'S' || Account_Type == 's') {

                        do {

                            System.out.print(" Please Enter your Account Number : ");
                            S_new_Account_No = sc.nextInt();


                            System.out.print(" Please Enter your 7-digit Pin Code : ");
                            S_new_Pin_Code = sc.next();

                            verifier1 = verifier2 = 0;
                            x1 = x2 = 0;
                            SA.verifyAlreadyExistingAccount(S_new_Account_No, S_new_Pin_Code);

                            System.out.println(Main.verifier1 + " verifier " + Main.verifier2);

                            if (verifier1 == 1 && verifier2 == 1) {
                                System.out.println("Account Verified.");
                            } else {
                                System.out.println("Account Verification Failed. Try Again!");

                            }


                        } while (verifier1 == 0 || verifier2 == 0);

                    } else if (Account_Type == 'C' || Account_Type == 'c') {
                        do {

                            System.out.print(" Please Enter your Account Number : ");
                            C_new_Account_No = sc.nextInt();


                            System.out.print(" Please Enter your 7-digit Pin Code : ");
                            C_new_Pin_Code = sc.next();

                            verifier1 = verifier2 = 0;
                            x1 = x2 = 0;
                            CA.verifyAlreadyExistingAccount(C_new_Account_No, C_new_Pin_Code);

                            System.out.println(Main.verifier1 + " verifier " + Main.verifier2);

                            if (verifier1 == 1 && verifier2 == 1) {
                                System.out.println("Account Verified.");
                            } else {
                                System.out.println("Account Verification Failed. Try Again!");

                            }


                        } while (verifier1 == 0 || verifier2 == 0);
                    }


                }

                do {

                    System.out.println("Choose which operation you want to do.\n Enter the number:");

                    System.out.println(" 1. Login To Account.");
                    System.out.println(" 2. Close the Account.");
                    System.out.println(" 3. Exit.");
                    choice = sc.nextInt();


                    if (choice == 1) {
                        System.out.println("Login Successful!");
                        System.out.println("Choose which operation you want to do.\n Enter the number:");

                        double Deposit_Amount = 0;
                        do {

                            System.out.println(" 1. Cash Deposit.");
                            System.out.println(" 2. Cash Withdrawal.");
                            System.out.println(" 3. Display All Details(of Account & Owner)");
                            System.out.println(" 4. Exit");
                            choice1 = sc.nextInt();

                            if (choice1 == 1)
                            {
                                if (Account_choice == 'A' || Account_choice == 'a' || Account_choice == 'N' || Account_choice == 'n')
                                {
                                    if (Account_Type == 'S' || Account_Type == 's')
                                    {
                                        System.out.println("Please, enter the Amount to Deposit : ");
                                        Deposit_Amount = sc.nextDouble();

                                        //Calling Function To Deposit Cash
                                        SA.makeDeposit(Deposit_Amount, Account_choice);

                                    }
                                    else if (Account_Type == 'C' || Account_Type == 'c')
                                    {
                                        System.out.println("Please, enter the Amount to Dposit : ");
                                        Deposit_Amount = sc.nextDouble();

                                        //Calling Function To Deposit Cash
                                        CA.makeDeposit(Deposit_Amount, Account_choice);

                                    }
                                }
                                System.out.println("Cash Deposited Successfully");


                            }
                            else if (choice1 == 2)
                            {
                                double Withdraw_Amount;
                                if (Account_choice == 'A' || Account_choice == 'a' || Account_choice == 'N' || Account_choice == 'n')
                                {
                                    if (Account_Type == 'S' || Account_Type == 's') {
                                        System.out.println("Please, enter the Amount to Withdraw : ");
                                        Withdraw_Amount = sc.nextDouble();

                                        //Calling Function To Deposit Cash
                                        SA.makeWithdrawl(Withdraw_Amount, Account_choice);

                                    } else if (Account_Type == 'C' || Account_Type == 'c') {
                                        System.out.println("Please, enter the Amount to Withdraw : ");
                                        Withdraw_Amount = sc.nextDouble();

                                        //Calling Function To Deposit Cash
                                        CA.makeWithdrawl(Withdraw_Amount, Account_choice);

                                    }

                                }
                                System.out.println("Cash Withdrawn Successfully.");

                            }
                            else if (choice1 == 3) {

                                if (Account_choice == 'N' || Account_choice == 'n')
                                {
                                    if (Account_Type == 'S' || Account_Type == 's')
                                    {
                                        //displaying all the deatils of a customer & its account
                                        SA.printStatement();
                                    }
                                    else if (Account_Type == 'C' || Account_Type == 'c')
                                    {
                                        //displaying all the deatils of a customer & its account
                                        CA.printStatement();
                                    }
                                } else if (Account_choice == 'A' || Account_choice == 'a') {
                                    if (Account_Type == 'S' || Account_Type == 's') {

                                        //Files objects for Reading
                                        File S10 = new File("SavingAccount_UserCNIC.txt");
                                        File S11 = new File("SavingAccount_UserNames.txt");
                                        File S12 = new File("SavingAccount_UserPhoneNo.txt");
                                        File S13 = new File("SavingAccount_UserAccountNumber.txt");
                                        File S14 = new File("SavingAccount_UserAddress.txt");
                                        File S15 = new File("SavingAccount_AccountPinCode.txt");
                                        File S16 = new File("SavingAccount_AccountBalance.txt");
                                        File S17 = new File("SavingAccount_AccountCreationDate&Time.txt");


                                        try {

                                            Scanner s2 = new Scanner(S10);
                                            Scanner s3 = new Scanner(S11);
                                            Scanner s4 = new Scanner(S12);
                                            Scanner s5 = new Scanner(S13);
                                            Scanner s6 = new Scanner(S14);
                                            Scanner s7 = new Scanner(S15);
                                            Scanner s8 = new Scanner(S16);
                                            Scanner s9 = new Scanner(S17);

                                            System.out.println("In main :" + x1 + " " + x2);

                                            //reading the CNINC
                                            while (s2.hasNextLine()) {
                                                lineCheck++;
                                                String d = s2.nextLine();

                                                if (lineCheck == x1 && lineCheck == x2) {
                                                    System.out.println(d);
                                                    S_new_CNIC = d;
                                                }
                                            }
                                            s2.close();


                                            //reading the Name
                                            lineCheck = 0;
                                            while (s3.hasNextLine()) {
                                                lineCheck++;
                                                String d = s3.nextLine();

                                                if (lineCheck == x1 && lineCheck == x2) {
                                                    System.out.println(d);
                                                    S_new_Name = d;
                                                }
                                            }
                                            s3.close();


                                            //reading the Phone Number
                                            lineCheck = 0;
                                            while (s4.hasNextLine()) {
                                                lineCheck++;
                                                String d = s4.nextLine();

                                                if (lineCheck == x1 && lineCheck == x2) {
                                                    System.out.println(d);
                                                    S_new_Phone_Number = d;
                                                }
                                            }
                                            s4.close();

                                            //reading the Account Number
                                            lineCheck = 0;
                                            while (s5.hasNextLine()) {
                                                lineCheck++;
                                                String d = s5.nextLine();

                                                if (lineCheck == x1 && lineCheck == x2) {
                                                    System.out.println(d);
                                                    S_new_Account_No = valueOf(d);
                                                }
                                            }
                                            s5.close();


                                            //reading the Address
                                            lineCheck = 0;
                                            while (s6.hasNextLine()) {
                                                lineCheck++;
                                                String d = s6.nextLine();

                                                if (lineCheck == x1 && lineCheck == x2) {
                                                    System.out.println(d);
                                                    S_new_Address = d;
                                                }
                                            }
                                            s6.close();

                                            //reading the Pin Code
                                            lineCheck = 0;
                                            while (s7.hasNextLine()) {
                                                lineCheck++;
                                                String d = s7.nextLine();

                                                if (lineCheck == x1 && lineCheck == x2) {
                                                    System.out.println(d);
                                                    S_new_Pin_Code = d;
                                                }
                                            }
                                            s7.close();


                                            //reading the Balance
                                            lineCheck = 0;
                                            while (s8.hasNextLine()) {
                                                lineCheck++;
                                                String d = s8.nextLine();

                                                if (lineCheck == x1 && lineCheck == x2) {
                                                    System.out.println(d);
                                                    S_New_Balance = Double.valueOf(d);
                                                }
                                            }
                                            s8.close();

                                            //reading the Date & Time
                                            lineCheck = 0;
                                            while (s9.hasNextLine()) {
                                                lineCheck++;
                                                String d = s9.nextLine();

                                                if (lineCheck == x1 && lineCheck == x2) {
                                                    System.out.println(d);
                                                    S_formattedDate = d;
                                                }
                                            }
                                            s9.close();


                                            //Passing Arguments to Constructor
                                            SA = new SavingAccount(S_new_CNIC, S_new_Name, S_new_Address, S_new_Account_No, S_new_Phone_Number, S_formattedDate, S_new_Pin_Code, S_New_Balance);


                                            //call function to display all the deatils of a customer & its account
                                            SA.printStatement();

                                        } catch (FileNotFoundException e) {
                                            System.out.println("File(s) did not read.");
                                            e.printStackTrace();
                                        }
                                    } else if (Account_Type == 'C' || Account_Type == 'c') {

                                        //Files objects for Reading
                                        File S10 = new File("CheckingAccount_UserCNIC.txt");
                                        File S11 = new File("CheckingAccount_UserNames.txt");
                                        File S12 = new File("CheckingAccount_UserPhoneNo.txt");
                                        File S13 = new File("CheckingAccount_UserAccountNumber.txt");
                                        File S14 = new File("CheckingAccount_UserAddress.txt");
                                        File S15 = new File("CheckingAccount_AccountPinCode.txt");
                                        File S16 = new File("CheckingAccount_AccountBalance.txt");
                                        File S17 = new File("CheckingAccount_AccountCreationDate&Time.txt");

                                        lineCheck = 0;
                                        try {

                                            Scanner s2 = new Scanner(S10);
                                            Scanner s3 = new Scanner(S11);
                                            Scanner s4 = new Scanner(S12);
                                            Scanner s5 = new Scanner(S13);
                                            Scanner s6 = new Scanner(S14);
                                            Scanner s7 = new Scanner(S15);
                                            Scanner s8 = new Scanner(S16);
                                            Scanner s9 = new Scanner(S17);

                                            System.out.println("In main :" + x1 + " " + x2);

                                            //reading the CNINC
                                            while (s2.hasNextLine()) {
                                                lineCheck++;
                                                String d = s2.nextLine();

                                                if (lineCheck == x1 && lineCheck == x2) {
                                                    System.out.println(d);
                                                    C_new_CNIC = d;
                                                }
                                            }
                                            s2.close();


                                            //reading the Name
                                            lineCheck = 0;
                                            while (s3.hasNextLine()) {
                                                lineCheck++;
                                                String d = s3.nextLine();

                                                if (lineCheck == x1 && lineCheck == x2) {
                                                    System.out.println(d);
                                                    C_new_Name = d;
                                                }
                                            }
                                            s3.close();


                                            //reading the Phone Number
                                            lineCheck = 0;
                                            while (s4.hasNextLine()) {
                                                lineCheck++;
                                                String d = s4.nextLine();

                                                if (lineCheck == x1 && lineCheck == x2) {
                                                    System.out.println(d);
                                                    C_new_Phone_Number = d;
                                                }
                                            }
                                            s4.close();

                                            //reading the Account Number
                                            lineCheck = 0;
                                            while (s5.hasNextLine()) {
                                                lineCheck++;
                                                String d = s5.nextLine();

                                                if (lineCheck == x1 && lineCheck == x2) {
                                                    System.out.println(d);
                                                    C_new_Account_No = valueOf(d);
                                                }
                                            }
                                            s5.close();


                                            //reading the Address
                                            lineCheck = 0;
                                            while (s6.hasNextLine()) {
                                                lineCheck++;
                                                String d = s6.nextLine();

                                                if (lineCheck == x1 && lineCheck == x2) {
                                                    System.out.println(d);
                                                    C_new_Address = d;
                                                }
                                            }
                                            s6.close();

                                            //reading the Pin Code
                                            lineCheck = 0;
                                            while (s7.hasNextLine()) {
                                                lineCheck++;
                                                String d = s7.nextLine();

                                                if (lineCheck == x1 && lineCheck == x2) {
                                                    System.out.println(d);
                                                    C_new_Pin_Code = d;
                                                }
                                            }
                                            s7.close();


                                            //reading the Balance
                                            lineCheck = 0;
                                            while (s8.hasNextLine()) {
                                                lineCheck++;
                                                String d = s8.nextLine();

                                                if (lineCheck == x1 && lineCheck == x2) {
                                                    System.out.println(d);
                                                    C_New_Balance = Double.valueOf(d);
                                                }
                                            }
                                            s8.close();

                                            //reading the Date & Time
                                            lineCheck = 0;
                                            while (s9.hasNextLine()) {
                                                lineCheck++;
                                                String d = s9.nextLine();

                                                if (lineCheck == x1 && lineCheck == x2) {
                                                    System.out.println(d);
                                                    C_formattedDate = d;
                                                }
                                            }
                                            s9.close();


                                            //Passing Arguments to Constructor
                                            CA = new CheckingAccount(C_new_CNIC, C_new_Name, C_new_Address, C_new_Account_No, C_new_Phone_Number, C_formattedDate, C_new_Pin_Code, C_New_Balance);


                                            //displaying all the deatils of a customer & its account
                                            CA.printStatement();

                                        }
                                        catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                            System.out.println("File did not read.");
                                        }
                                    }
                                }
                            }



                        } while (choice1 == 1 || choice1 == 2 || choice1 == 3) ;

                    } else if (choice == 2) {
                        System.out.println("Account Closed.\n\n");

                    } else if (choice == 3) {

                    }
                } while (choice == 1 || choice == 2) ;


            }
            while (choice == 2 || choice == 3 || choice1 == 4);
        } while (validation == 1);

    }
}



