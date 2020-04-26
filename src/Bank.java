
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bank {
	private BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
	private BankTeller ATM = new BankTeller();

	public static void main(String[] args) {
		greeting();
		usage();
		Bank bankApp = new Bank();

		try {
			String command = bankApp.readLine("--> ");
			while (!command.equals("quit")) {
				try {
					if (command.equals("open")) {
						bankApp.open();
					} else if (command.equals("deposit")) {
						bankApp.doDeposit();
					} else if (command.equals("withdraw")) {
						bankApp.doWithdraw();
					} else if (command.equals("currency exchange")) {
						bankApp.doExchange();
					} else if (command.equals("inquire")) {
						bankApp.doInquire();
					} else {
						System.err.println("Invalid command: " + command);
						usage();
					}
					command = bankApp.readLine("--> ");
				} catch (BadAccountException e1) {
					System.out.println(e1);
				} catch (IOException e) {
					System.err.println(e);
				}

			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * open() prompts the user to create an account and creates one in the ATM.
	 * 
	 * @exception IOException if there are problems reading user input.
	 */
	private void open() throws IOException {
		String name = readLine("Enter name: ");
		int newNum = ATM.openAccount(name);

		System.out.println(name + ", your new account number is: " + newNum);
		System.out.println("Thanks for opening an account with us!");
	}

	/**
	 * Prompts the user for an account number and tries to perform a deposit
	 * transaction on that account.
	 * 
	 * @exception IOException if there are problems reading user input.
	 */
	private void doDeposit() throws IOException, BadAccountException {
		// Get account number.
		int acctNumber = readInt("Enter account number: ");
		String str = readLine("Enter yout name: ");
		if (!str.equals(ATM.findAccount(acctNumber).getOwner())){
			System.out.println("Name do not match; Deny access");
			return;
		}
		int amount = readInt("Enter amount to deposit: ");

		ATM.deposit(acctNumber, amount);
		System.out.println("New balance for #" + acctNumber + " is " + ATM.balanceInquiry(acctNumber));
	}

	/**
	 * Prompts the user for an account number and tries to perform a withdrawal
	 * transaction from that account.
	 * 
	 * @exception IOException if there are problems reading user input.
	 */
	private void doWithdraw() throws IOException, BadAccountException {
		// Get account number.
		int acctNumber = readInt("Enter account number: ");
		String str = readLine("Enter yout name: ");
		if (!str.equals(ATM.findAccount(acctNumber).getOwner())){
			System.out.println("Name do not match; Deny access");
			return;
		}
		int amount = readInt("Enter amount to withdraw: ");

		ATM.withdraw(acctNumber, amount);
		System.out.println("New balance for #" + acctNumber + " is " + ATM.balanceInquiry(acctNumber));
	}
	
	/**
	 * Prompts the user for an account number and tries to perform a currency exchange
	 * transaction from that account.
	 * @throws IOException
	 * @throws BadAccountException
	 */
	private void doExchange() throws IOException, BadAccountException {
		// Get account number.
		int acctNumber = readInt("Enter account number: ");
		String str = readLine("Enter yout name: ");
		if (!str.equals(ATM.findAccount(acctNumber).getOwner())){
			System.out.println("Name do not match; Deny access");
			return;
		}
		Map<String, Double> exchangeRate = new HashMap<String, Double>();
		exchangeRate.put("EUR", 0.93);
		exchangeRate.put("GBP", 0.81);
		exchangeRate.put("CAD", 1.41);
		exchangeRate.put("JPY", 107.45);
		exchangeRate.put("HKD", 7.75);
		System.out.println("We provide currency exchange for EUR, GBP, CAD，JPY, HKD.");
		String currency = readLine("Enter the currency: ");
		int exchange = readInt("Enter amount to exchange(in USD): ");
		int amount = (int)(exchangeRate.get(currency)*exchange);
		ATM.withdraw(acctNumber, exchange);
		ATM.foreignDeposit(acctNumber, amount);
		System.out.println("New balance for #" + acctNumber + " is " + ATM.balanceInquiry(acctNumber));
		System.out.println("New foreign currency balance for #" + acctNumber + " is " + ATM.foreignBalanceInquiry(acctNumber)+ " "+currency);
	}

	/**
	 * Prompts the user for an account number, then attempts to discover and print
	 * that account's balance.
	 * 
	 * @exception IOException if there are problems reading user input.
	 */
	private void doInquire() throws IOException, BadAccountException {
		int acctNumber = readInt("Enter account number: ");
		String str = readLine("Enter yout name");
		if (!str.equals(ATM.findAccount(acctNumber).getOwner())){
			System.out.println("Name do not match; Deny access");
			return;
		}
		System.out.println("Balance for #" + acctNumber + " is " + ATM.balanceInquiry(acctNumber));
		System.out.println("Balance for #" + acctNumber + " is " + ATM.foreignBalanceInquiry(acctNumber));
	}

	/**
	 * Displays a greeting message on the screen.
	 */
	private static void greeting() {
		System.out.println("-------------------");
		System.out.println("Welcome to the bank");
		System.out.println("-------------------");
		System.out.println();
	}

	/**
	 * displays instructions on using the command line arguments.
	 */
	private static void usage() {
		System.out.println("Valid commands are: " + "open, deposit, withdraw, currency exchange, inquire, quit");
	}

	/**
	 * Prints the given prompt and returns a string from the input stream
	 */
	private String readLine(String prompt) throws IOException {
		System.out.print(prompt);
		System.out.flush();
		return bReader.readLine();
	}

	/**
	 * readInt() returns an integer from the input stream after prompting the user.
	 * 
	 * @param prompt is the string printed to prompt the user.
	 * @return an int read from the user.
	 */
	private int readInt(String prompt) throws IOException {
		String text = readLine(prompt);
		return Integer.valueOf(text).intValue();
	}
}