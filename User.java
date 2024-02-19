/**
 * This class stores information for individual users, such as login information.
 */
public class User {
	
	private String username;
	private String password;
	private int checking;
	private int savings;
	
	/**
	 * @return checking the amount in the checking account
	 */
	public int getChecking() {
		return checking;
	}

	/**
	 * @param checking the amount that the checking account will be changed too
	 */
	public void setChecking(int checking) {
		this.checking = checking;
	}

	/**
	 * @return savings the amount in the savings account
	 */
	public int getSavings() {
		return savings;
	}

	/**
	 * @param checking the amount that the saving account will be changed too
	 */
	public void setSavings(int savings) {
		this.savings = savings;
	}

	/**
	 * @return username the username of the account
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return password the password of the account
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This constructs the user and their accounts
	 * 
	 * @param name Sets the username of a user
	 * @param password Sets the password for a user
	 * @param checking Sets and stores the checking account for the user
	 * @param saving Sets and stores the Savings account for the user
	 */
	public User(String name, String password, int checking, int saving) {
		username = name;
		this.password = password;
		this.checking = checking;
		this.savings = saving;
	}
}
