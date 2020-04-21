public class BankTeller {
	private static int nextAccountID = 100;
	private SortedList accounts;

	public BankTeller() {
		accounts = new SortedList();
	}

	/**
	 * Creates a new account for the customer "name".
	 * 
	 * @param name the customer's name.
	 * @return the new account's ID number.
	 **/
	public int openAccount(String name) {
		AccountData newData = new AccountData(name, nextAccountID);
		accounts.insert(newData);

		nextAccountID++;
		return newData.getNumber();
	}

	/**
	 * Withdraws "amount" dollars from the account whose number is "acct". Assumes
	 * that amount >= 0. If "acct" is invalid, no action is taken.
	 * 
	 * @param acct   is an account number.
	 * @param amount an amount of money.
	 */
	public void withdraw(int acct, int amount) throws BadAccountException {
		if (amount < 0) {
			System.out.println("It is invalid to withdraw negative amount of money");
			return;
		}
		AccountData account = findAccount(acct);

		if (account != null) {
			account.withdraw(amount);
		}
	}

	/**
	 * Deposits "amount" dollars into the bank account whose number is "acct".
	 * Assumes that amount >= 0. If "acct" is invalid, no action is taken.
	 * 
	 * @param acct   is an account number.
	 * @param amount an amount of money.
	 */
	public void deposit(int acct, int amount) throws BadAccountException {
		AccountData account = findAccount(acct);

		if (account != null) {
			account.deposit(amount);
		}

	}

	/**
	 * Finds the balance on the account whose number is "acct". If "acct" is an
	 * invalid number, returns -1.
	 * 
	 * @param acct an account number.
	 * @return the balance, or -1 if the account number is invalid.
	 */
	public int balanceInquiry(int acct) throws BadAccountException {
		AccountData account = findAccount(acct);

		if (account == null) {
			System.out.println("Error:  Couldn't find account number `" + acct + "'");
			return -1;
		} else {
			return account.getBalance();
		}
	}

	/**
	 * Gets the AccountData object associated with account number "acct". If "acct"
	 * does not refer to a valid account, returns null.
	 * 
	 * @param acct is an account number.
	 * @return the AccountData object associated with the account number.
	 * @throws BadAccountException
	 */
	private AccountData findAccount(int acct) throws BadAccountException {
		AccountData account = (AccountData) accounts.find(acct);
		if (account == null) {
			throw new BadAccountException(acct);
		}
		return account;
	}
}
