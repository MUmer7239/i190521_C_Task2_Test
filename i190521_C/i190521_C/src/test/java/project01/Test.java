package project01;

import static org.junit.Assert.*;

import org.junit.Test;

@Test   // Positive
public void P_test()
		{
		SavingAccount  S = new SavingAccount();

		boolean O = test.pwd(3112121);

		assertEquals(true, O);
		}

		@Test
          //Negative
public void N_test()
		{
		SavingAccount  S = new SavingAccount();

		boolean O= test.password(-1289377);

		assertEquals(false,O);
		}


@Test   // Positive
public void P_test1()
		{
		CheckingAccount  S = new CheckingAccount();

		boolean O = test.pwd(3112121);

		assertEquals(true, O);
		}

@Test
//Negative
public void N_test1()
		{
		CheckingAccount  S = new CheckingAccount();

		boolean O= test.password(-1289377);

		assertEquals(false,O);
		}

@Test
//Positive
public void Account_Validation()
		{
		File_Handling t = new File_Handling();

		String Account_no = String.valueOf(9027362);

		boolean c;

		c = ValidateAccount(31204-9812783-5);

		assertEquals(true,c);
		}
@Test
//Negative
public void Account_Validation1()
		{
		File_Handling t = new File_Handling();

		String Account_no = String.valueOf(9027362);

		boolean c;

		c = ValidateAccount(-31204-9812783-5);

		assertEquals(false,c);
		}

@Test
//Positive
public void verifyAlreadyExistingAccount()
		{
		File_Handling t = new File_Handling();
		String CNIC_No = "31302-5381901-1";

		boolean a;

		a = t.verifyAlreadyExistingAccount("SavingAccount_UserCNIC.txt", CNIC_No);
		assertEquals(true,a);
		}

@Test
//Negative
public void verifyAlreadyExistingAccount1()
		{
		File_Handling t = new File_Handling();
		String CNIC_No = "17301-5881502-1";

		boolean a;

		a = t.verifyAlreadyExistingAccount("SavingAccount_UserCNIC.txt", CNIC_No);
		assertEquals(false,a);
		}



@Test
public void checkBalance()
		{
			SavingAccount test= new SavingAccount();

			String A = test.checkBalance(72381983);

			assertEquals("2738913", A);
		}

@Test
public void checkBalance1()
		{
		CheckingAccount test= new CheckingAccount();

		String A = test.checkBalance(72381983);

		assertEquals("2738913", A);
		}


