/**
 * This class stores the userdatabase, along with utilizing the information within the database
 */
public class Bank{
	
	private UserDatabase data; 
	
	/**
	 * This initializes the User database
	 */
	public Bank() {
		data = new UserDatabase();
	}
	
	/**
	 * This returns the Operator account of the ATM
	 * 
	 * @return admin the operator of the ATM
	 */
	public User getOperator() {
		return data.admin;
	}
	
	/**
	 * This returns a desired Customer if they exist in the database
	 * 
	 * @param UserName the username of the desired account
	 * @param Password the password of the desired account
	 * @return user if the account exists in the database
	 * @return null if the account does not exist
	 */
	public User getCustomer(String UserName, String Password) {
		for (int i = 0; i < data.CustomerList.length; i++) {
			if(UserName.equals(data.CustomerList[i].getUsername()) && Password.equals(data.CustomerList[i].getPassword())) {
				return data.CustomerList[i];
				//this loop checks if a user exists in the system and returns them if they are
			}
		}
		return null;
	}
	
	/**
	 * This method returns the Userdatabase for extended commands
	 * 
	 * @return data the Userdatabase for commands using it
	 */
	public UserDatabase getData() {
		return data;
	}
}
