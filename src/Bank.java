
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		int amount = readInt("Enter amount to withdraw: ");

		ATM.withdraw(acctNumber, amount);
		System.out.println("New balance for #" + acctNumber + " is " + ATM.balanceInquiry(acctNumber));
	}

	/**
	 * Prompts the user for an account number, then attempts to discover and print
	 * that account's balance.
	 * 
	 * @exception IOException if there are problems reading user input.
	 */
	private void doInquire() throws IOException, BadAccountException {
		int acctNumber = readInt("Enter account number: ");

		System.out.println("Balance for #" + acctNumber + " is " + ATM.balanceInquiry(acctNumber));
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
		System.out.println("Valid commands are: " + "open, deposit, withdraw, inquire, quit");
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