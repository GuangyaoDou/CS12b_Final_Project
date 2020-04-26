import java.util.*;

public class AccountData{
	private String name; // Customer name.
	private int balance; // Starting balance.
	private int number; // Account number.
	private Map<String, Integer> foreignAccount; 
	private int foreignBalance; //balance in foreign currency account.
	private String foreignCurType;

	public AccountData(String newName, int num) {
		name = newName;
		number = num;
		balance = 0;
		foreignBalance = 0;
		foreignCurType = "";
		foreignAccount = new HashMap<String, Integer>();
	}

	/**
	 * Returns the name of this account's owner.
	 **/
	public String getOwner() {
		return name;
	}

	/**
	 * Returns a String version of this account's number.
	 **/
	public String toString() {
		return "" + number;
	}

	/**
	 * Returns the balance of this account.
	 **/
	public int getBalance() {
		return balance;
	}
		
	/**
	 * Returns the foreign account
	 */
	public Map<String, Integer> getForeignAccount() {
		return foreignAccount;
	}
	
	/**
	 * Reduces the balance by the withdrawal amount "amt".
	 **/
	public void withdraw(int amt) {
		if (amt <= balance) {
			balance = balance - amt;
		} else {
			System.out.println("Error:  Insufficient funds: " + amt);
		}
	}

	/**
	 * Deposits "amount" dollars into this account.
	 **/
	public void deposit(int amt) {
		if (amt >= 0) {
			balance = balance + amt;
		} else {
			System.out.println("Error:  Tried to deposit less than 0: " + amt);
		}
	}
	
	/**
	 * deposit amt foreign currency into the account.
	 * @param amt
	 */
	public void foreignDeposit(String type, int amt) {
		if (amt >= 0) {
			foreignCurType = type;
			if (foreignAccount.containsKey(foreignCurType)) {
				foreignBalance = foreignAccount.get(type) + amt;
			} else {
				foreignBalance = amt;
			}	
			foreignAccount.put(foreignCurType, foreignBalance);
		} else {
			System.out.println("Error:  Tried to deposit less than 0: " + amt);
		}
	}

	/**
	 * Returns this account's number.
	 **/
	public int getNumber() {
		return number;
	}
}
