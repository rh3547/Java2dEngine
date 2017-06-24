package com.engine.data;

/**
 * <h1>Bank</h1>
 * A bank is a basic numerical database.
 * It can be used for money as a bank typically would,
 * but can also be used to store any other
 * numerical data for other uses, such as
 * experience points, skill points, etc.
 * 
 * @author Ryan Hochmuth
 *
 */
public class Bank
{
	/*
	 * Transaction Types
	 */
	/**
	 * <h1>Transaction Type</h1>
	 * ADD: Add to this bank.
	 */
	public static final int ADD = 0;
	/**
	 * <h1>Transaction Type</h1>
	 * SUBTRACT: Subtract from this bank.
	 */
	public static final int SUBTRACT = 1;
	
	private double balance; // The amount of money in this bank
	
	/**
	 * Create a new bank with the given
	 * initial balance.
	 * @param initialBalance
	 */
	public Bank(double initialBalance)
	{
		this.balance = initialBalance;
	}
	
	/**
	 * Alter the balance of this bank.  To reduce
	 * the amount of methods, a transaction type
	 * is required as a parameter.  See transaction
	 * types in Bank.
	 * @param amount - the amount to change by
	 * @param type - the type of transaction (add, subtract, etc.)
	 */
	public boolean changeBalance(double amount, int type)
	{
		if (amount <= 0)
			throw new NumberFormatException("The amount must be greater than 0.");
		
		if (checkTransaction(amount, type))
		{
			switch(type)
			{
				case Bank.ADD:
					this.balance += amount;
					break;
					
				case Bank.SUBTRACT:
					this.balance -= amount;
					break;
			}
			
			return true;
		}
		else
		{
			switch(type)
			{
				case Bank.ADD:
					System.out.println("Adding " + amount + " to your funds would exceed the max value of 999,999.");
					break;
					
				case Bank.SUBTRACT:
					System.out.println("Insufficient funds.");
					break;
			}
			
			return false;
		}
	}
	
	/**
	 * Check if the given transaction can be completed
	 * with the bank in its current state.  ex. If trying
	 * to subtract more from the bank than possible, this
	 * method will return false and not allow the transaction.
	 * @param amount - the amount to change by
	 * @param type - the type of transaction (add, subtract, etc.)
	 * @return boolean - if the transaction is possible
	 */
	public boolean checkTransaction(double amount, int type)
	{
		if (amount <= 0)
			throw new NumberFormatException("The amount must be greater than 0.");
		
		switch(type)
		{
			case Bank.ADD:
				if ((this.balance + amount) <= 999999)
					return true;
				break;
				
			case Bank.SUBTRACT:
				if ((this.balance - amount) >= 0)
					return true;
				break;
		}
		
		return false;
	}

	/**
	 * @return the balance
	 */
	public double getBalance()
	{
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
}