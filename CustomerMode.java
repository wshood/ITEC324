/**
 * This class holds the commands to run Customer Mode on the ATM
 */
public class CustomerMode{
	
	private UserDatabase users;
	private User currentUser;
	
	/**
	 * @param users the database of all users
	 * @param current the current active user
	 */
	public CustomerMode(UserDatabase users, User current) {
		this.users = users;
		currentUser = current;
	}
	
	/**
	 * Shows the current Balance of both checking and savings
	 */
	public void displayBalance() {
		System.out.println("Checking: " + users.getUsersBalance(currentUser, "checking"));
		System.out.println("Savings: " + users.getUsersBalance(currentUser, "saving"));
	}
	
	/**
	 * starts the transfer method in the database between differing accounts
	 * 
	 * @param user2 the username of the second user
	 */
	public void transfer(String user2) {
		users.transferMoney(currentUser, user2);
	}
	
	/**
	 * Enacts the deposit of funds into a customer account
	 * 
	 * @param account the account of choice for deposit
	 * @param amount the desired amount to deposit
	 */
	public void deposit(String account, int amount) {
		if(account.equals("checking")) {
			currentUser.setChecking(currentUser.getChecking() + amount);
		}
		//allows for a user to input into checking or savings, dependent on input
		currentUser.setSavings(currentUser.getSavings() + amount);
	}
	
	/**
	 * Enacts the withdrawal of funds from a customer account
	 * 
	 * @param account the account of choice for withdrawal
	 * @param amount the desired amount to withdrawal
	 */
	public void withdrawl(String account, int amount) {
		if(account.equals("checking")) {
			currentUser.setChecking(currentUser.getChecking() - amount);
		}
		//allows for a user to extract from checking or savings
		currentUser.setSavings(currentUser.getSavings() - amount);
	}

}
