import java.util.Scanner;

/**
 * This class is the ATM itself, it handles the display and interface of the System
 */
public class ATMSystem{

	private Bank bank;
	private Vault vault;
	private OperationsMode opmode;
	private User currentUser;
	private CustomerMode cmmode = null;
	private OperatorMode ommode = null;
	//the CustomerMode and OperatorMode note the current state of the ATM
	
	/**
	 * Consturcts the needs of the ATM, and handles the login process, starting the program
	 * 
	 * @param bank this holds the Userdatabase for the system
	 */
	public ATMSystem(Bank bank) {
		vault = new Vault();
		this.bank = bank;
		Scanner scanner = new Scanner(System.in);
		String username;
		String password;
		//stores the username and password for the login process
		currentUser = null;
		//holds the logged in user
		User admin = bank.getOperator();
		//holds the operator for reference
		boolean truelogin = false;
		while(truelogin == false) {
			System.out.println("Please Login to your account\nStart by entering your username");
			username = scanner.nextLine();
			System.out.println("Now please enter your password");
			password = scanner.nextLine();
			if(username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
				currentUser = bank.getOperator();
				//determines if a operator account matches with the bank
			}else {
				currentUser = bank.getCustomer(username, password);
			}
			if(currentUser != null) {
				System.out.println("Login Successful");
				truelogin = true;
				//stops the cycle if the current user logs in with an account in the database
			}else {
				System.out.println("Invalid Account, please try again");
			}
		}
		//while loops allow for the continuous handling of a condition until satisfied 
		switchToOpMode();

	}
	
	/**
	 * This method takes the information handed from the OperationsMode class and determines the state from it
	 */
	public void switchToOpMode() {
		opmode = new OperationsMode(currentUser);
		opmode.SwitchtoOpMode(vault, bank.getData());
		cmmode = opmode.getCmmode();
		ommode = opmode.getOmmode();
		//takes the CustomerMode and OperatorMode from opmode and determines the state below
		if(cmmode != null) {
			displayCustomerMode();
		}else if(ommode != null) {
			displayOperatorMode();
		}
	}
	
	/**
	 * This displays the actions taken within CustomerMode
	 */
	public void displayCustomerMode() {
		if(cmmode != null) {
			//saves the method from a bad call to CustomerMode
			boolean loggedin = true;
			System.out.println("Welcome " + currentUser.getUsername() + "\nWhat would you like to do today?");
			Scanner scanner = new Scanner(System.in);
			String input = null;
			while(loggedin == true) {
				System.out.println("Deposit funds");
				System.out.println("Withdrawal funds");
				System.out.println("Transfer money");
				System.out.println("View account balance");
				System.out.println("View vault balance (requires operator)");
				System.out.println("Logout");
				input = scanner.nextLine();
				//the input determines which actions are taken in the program
				switch(input) {
				case "deposit":
					//in case deposit is chosen, takes the appropriate actions for it
					boolean validAccount = false;
					String account = null;
					while(validAccount == false) {
						System.out.println("Which account would you like to put money into?");
						account = scanner.nextLine();
						if(account.equals("checking") || account.equals("savings")) {
							validAccount = true;
						}
						else {
							System.out.println("Please enter a valid account");
						}
					}
					int amount = 0;
					boolean validAmount = false;
					while(validAmount == false) {
						System.out.println("How much would you like to deposit?");
						amount = scanner.nextInt();
						if(amount > 0) {
							validAmount = true;
						}else {
							System.out.println("Please enter an amount Greater than 0");
						}
					}
					cmmode.deposit(account, amount);
					//sends the command to deposit to the customer class after information has been sourced
					System.out.println("Deposit Successful");
					break;
				case "withdrawal":
					//In case of the withdrawal option, this gives the current list of those actions
					boolean validAccount2 = false;
					String account2 = null;
					//These are labeled separately from the ones in the deposit case for clarity and no overlapping parameters
					while(validAccount2 == false) {
						System.out.println("Which account would you like take money out of?");
						account2 = scanner.nextLine();
						if((account2.equals("checking") && currentUser.getChecking() > 0) || (account2.equals("savings") && currentUser.getSavings() > 0)) {
							validAccount2 = true;
							//for withdrawals, this checks to both have a account name, but if it has money to take away as well
						}
						else {
							System.out.println("Please enter a valid account");
						}
					}
					boolean validAmount2 = false;
					int amount2 = 0;
					while(validAmount2 == false) {
						System.out.println("How much would you like to withdrawal?");
						amount2 = scanner.nextInt();
						if(amount2 > 0) {
							if(account2.equals("checking")) {
								if(amount2 > currentUser.getChecking()) {
									System.out.println("Please input an amount less than what is in your account");
									//ensures no withdrawals can occur that leave a negative balance
								}else {
									if(amount2 % 5 != 0) {
										System.out.println("Please input a value that can be divisible by 5");
										//this requirement prevents the ATM from withdrawing bills it cannot process
									}else {
										validAmount2 = true;
									}
								}
							}else if(account2.equals("savings")) {
								if(amount2 > currentUser.getSavings()) {
									System.out.println("Please input an amount less than what is in your account");
									//ensures no withdrawals can occur that leave a negative balance
								}else {
									if(amount2 % 5 != 0) {
										System.out.println("Please input a value that can be divisible by 5");
										//this requirement prevents the ATM from withdrawing bills it cannot process
									}else {
										validAmount2 = true;
									}
								}
							}
						}else {
							System.out.println("Please enter an amount Greater than 0");
						}
					}
					//once the amount of transfer has been finalized, it checks to see if the ATM can process the amount
					boolean validity = vault.withdrawalMath(amount2);
					if(validity == true) {
						cmmode.deposit(account2, amount2);
						System.out.println("Withdrawal Successful");
					}else {
						System.out.println("The ATM currently does not have the funds to support this withdrawal\nYour Transaction was cancelled\nwe apologize for the inconvience");
						//This ensures that the action cannot happen if the funds are not in the ATM
					}
					break;
				case "transfer":
					//This is when the transfer option is used
					boolean userToTransfer = false;
					String usrToTransfer = null;
					while(userToTransfer == false) {
						System.out.println("Who would you like to transfer money too\n(press space if you are transferring between your own accounts)");
						usrToTransfer = scanner.nextLine();
						userToTransfer = true;
						//the second user is identified here (if applicable) then sent to the CustomerMode class method
						cmmode.transfer(usrToTransfer);
					}
					break;
				case "view account":
					//this is when the user selects to view their balance
					cmmode.displayBalance();
					break;
				case "logout":
					//this allows the program to end
					loggedin = false;
					break;
				case "view vault":
					//this allows the vault balance to be seen, with an operator password
					boolean isAdmin = false;
					String password = null;
					User admin = bank.getOperator();
					//used for admin verification
					while(!isAdmin) {
						System.out.println("Please enter the admin password");
						password = scanner.nextLine();
						if(password.equals(admin.getPassword())) {
							vault.displayVaultBalance();
							isAdmin = true;
						}else {
							System.out.println("Incorrect: please try again");
						}
						//this ensures that no one but the admin can use this feature
					}
					break;
				default:
					//if no valid commands are entered, this shows
					System.out.println("Please enter a valid operating mode");
				}
			}
		}
	}
	
	/**
	 * This method displays the operatorMode
	 */
	public void displayOperatorMode() {
		if(ommode != null) {
			boolean loggedin = true;
			Scanner scanner = new Scanner(System.in);
			String input = null;
			//same visuals of the CustomerMode, with differing inputs
			System.out.println("Welcome Operator\nWhat Would you like to do today");
			while(loggedin == true) {
				System.out.println("Add bills to ATM");
				System.out.println("Remove bills to ATM");
				System.out.println("View number of bills");
				System.out.println("Logout");
				input = scanner.nextLine();
				//allows for differing inputs
				switch(input) {
				case "add bills":
					//selected if the operator wants to add bills
					int insert = 0;
					boolean value = false;
					//each bill utilizes the same feature, where a bill asks how many of each type one wants to add
					System.out.println("How many $5 bills do you want to add?");
					while(value == false) {
						insert = scanner.nextInt();
						if(insert < 0) {
							System.out.println("Please put a value of 0 or higher");
						}else {
							vault.setFiveDollarBills(vault.getFiveDollarBills() + insert);
							//the current bill amount is added onto with the count of insert
							value = true;
						}
					}
					System.out.println("How many $20 bills do you want to add?");
					value = false;
					while(value == false) {
						insert = scanner.nextInt();
						if(insert < 0) {
							System.out.println("Please put a value of 0 or higher");
						}else {
							vault.setTwentyDollarBills(vault.getTwentyDollarBills() + insert);
							value = true;
						}
					}
					System.out.println("How many $50 bills do you want to add?");
					value = false;
					while(value == false) {
						insert = scanner.nextInt();
						if(insert < 0) {
							System.out.println("Please put a value of 0 or higher");
						}else {
							vault.setFiftyDollarBills(vault.getFiftyDollarBills() + insert);
							value = true;
						}
					}
					System.out.println("How many $100 bills do you want to add?");
					value = false;
					while(value == false) {
						insert = scanner.nextInt();
						if(insert < 0) {
							System.out.println("Please put a value of 0 or higher");
						}else {
							vault.setHundredDollarBills(vault.getHundredDollarBills() + insert);
							value = true;
						}
					}
					break;
				case "remove bills":
					//This is the operating menu if the operator desires to remove bills
					int insert2 = 0;
					boolean value2 = false;
					//Very similar to the add bills function, where it will cycle through the types of bills 
					System.out.println("How many $5 bills do you want to remove?");
					while(value2 == false) {
						insert2 = scanner.nextInt();
						if(insert2 < 0) {
							System.out.println("Please put a value of 0 or higher");
						}else if(insert2 <= vault.getFiveDollarBills()){
							vault.setFiveDollarBills(vault.getFiveDollarBills() - insert2);
							//the current bill amount is subtracted with the count of insert
							value2 = true;
						}else {
							System.out.println("Please put a value smaller than the balance");
						}
						//the key difference in this loop is that one cannot remove more bills than the ATM has
					}
					System.out.println("How many $20 bills do you want to remove?");
					value2 = false;
					while(value2 == false) {
						insert2 = scanner.nextInt();
						if(insert2 < 0) {
							System.out.println("Please put a value of 0 or higher");
						}else if(insert2 <= vault.getTwentyDollarBills()){
							vault.setTwentyDollarBills(vault.getTwentyDollarBills() - insert2);
							value2 = true;
						}else {
							System.out.println("Please put a value smaller than the balance");
						}
					}
					System.out.println("How many $50 bills do you want to remove?");
					value2 = false;
					while(value2 == false) {
						insert2 = scanner.nextInt();
						if(insert2 < 0) {
							System.out.println("Please put a value of 0 or higher");
						}else if(insert2 <= vault.getFiftyDollarBills()){
							vault.setFiftyDollarBills(vault.getFiftyDollarBills() - insert2);
							value2 = true;
						}else {
							System.out.println("Please put a value smaller than the balance");
						}
					}
					System.out.println("How many $100 bills do you want to remove?");
					value2 = false;
					while(value2 == false) {
						insert2 = scanner.nextInt();
						if(insert2 < 0) {
							System.out.println("Please put a value of 0 or higher");
						}else if(insert2 <= vault.getHundredDollarBills()){
							vault.setHundredDollarBills(vault.getHundredDollarBills() - insert2);
							value2 = true;
						}else {
							System.out.println("Please put a value smaller than the balance");
						}
					}
					break;
				case "view bills":
					//This displays how many of each bill is stored in the vault
					vault.displayVaultStatus();
					break;
				case "logout":
					//This logs out the admin account and ends the program
					loggedin = false;
					break;
				default:
					//if no valid action is occuring, this triggers
					System.out.println("Please select a valid operation");
				}
			}
		}
	}
}
