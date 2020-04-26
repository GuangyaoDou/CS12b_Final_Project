
public class AccountData implements Keyable {
	private String name; // Customer name.
	private int balance; // Starting balance.
	private int number; // Account number.
	private int foreignBalance; //balance in foreign account.

	public AccountData(String newName, int num) {
		name = newName;
		number = num;
		balance = 0;
		foreignBalance = 0;
	}

	/**
	 * Returns true if this account's number is less than the argument's account
	 * number.
	 **/
	public boolean lessThan(Keyable x) {
		return number < ((AccountData) x).number;
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
	 * Returns the foreign currency balance of this account.
	 **/
	public int getForeignBalance() {
		return foreignBalance;
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
	public void foreignDeposit(int amt) {
		if (amt >= 0) {
			foreignBalance = foreignBalance + amt;
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

	/**
	 * Returns this account's account number as the key to use for sorting and
	 * comparison
	 **/
	public int getKey() {
		return number;
	}
}
