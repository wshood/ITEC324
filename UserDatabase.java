import java.util.Random;
import java.util.Scanner;

/**
 *This class stores the users and handles user transfers
*/

public class UserDatabase {
	
	public User admin;
	public User user1;
	public User user2;
	public User user3;
	public User user4;
	public User user5;
	public User user6;
	public User user7;
	public User user8;
	public User user9;
	public User user10;
	//list of pregenerated users
	public User[] CustomerList;
	//this stores the users in a singular database
	
	/**
	 * This constructs the userDatabase by creating the customers and operator
	 */
	public UserDatabase() {
		CreateCustomer();
		CreateOperator();
	}
		
	/**
	 * This creates and initializes the customers, including checking and savings accounts
	 */
	public void CreateCustomer() {
		Random random = new Random();
		//this creates an amount in the pre-generated testing accounts
		user1= new User("User1", "Password1", random.nextInt(20000), random.nextInt(20000));
		user2= new User("User2", "Password2", random.nextInt(20000), random.nextInt(20000));
		user3= new User("User3", "Password3", random.nextInt(20000), random.nextInt(20000));
		user4= new User("User4", "Password4", random.nextInt(20000), random.nextInt(20000));
		user5= new User("User5", "Password5", random.nextInt(20000), random.nextInt(20000));
		user6= new User("User6", "Password6", random.nextInt(20000), random.nextInt(20000));
		user7= new User("User7", "Password7", random.nextInt(20000), random.nextInt(20000));
		user8= new User("User8", "Password8", 0, random.nextInt(20000));
		user9= new User("User9", "Password9", random.nextInt(20000), 0);
		user10= new User("User10", "Password10", random.nextInt(20000), random.nextInt(20000));
		User[] CustomerList = {user1, user2, user3, user4, user5, user6, user7, user8, user9, user10};
		//initializes the ArrayList to store all of the users
		this.CustomerList = CustomerList;
	}
	
	/**
	 * Creates a user of admin
	 */
	public void CreateOperator() {
		admin = new User("admin", "password", 0, 0);
	}
	
	/**
	 * Gets the total account balance of a user
	 * 
	 * @param user the current user
	 * @param account the account of the user
	 * @return
	 */
	public int getUsersBalance(User user, String account) {
		if(account.equals("checking")) {
			return user.getChecking();
		}
		return user.getSavings();
	}
	
	/**
	 * @param user1 the current user
	 * @param user2name the username of the user recieving a transfer
	 */
	public void transferMoney(User user1, String user2name) {
		Scanner scanner = new Scanner(System.in);
		//allows for input of needed information
		User user2 = null;
		boolean validUser = false;
		while(validUser == false && !(user2name.equals(" "))) {
			for(int i = 0; i < CustomerList.length; i++) {
				if(CustomerList[i].getUsername().equals(user2name)) {
					user2 = CustomerList[i];
					validUser = true;
				}
			}
			System.out.println("This user could not be found, please enter a different user");
			user2name = scanner.nextLine();
		}
		//finds a valid account for the username provided of the recipient
		String account = null;
		boolean validAccount = false;
		if(user2name.equals(" ")) {
			//the " " is the value of transferring between a user's own different accounts
			while(validAccount == false) {
				System.out.println("Which account would you like to transfer from?\nChecking or Savings?");
				account = scanner.nextLine();
				if(account.equals("checking") || account.equals("savings")) {
					//selects between savings and checking accounts
					validAccount = true;
				}else {
					System.out.println("Please select between Checking or savings");
				}
			}
			if(user1 != null) {
				//prevents the transfer starting from a nonexistent account
				int transferbalance = getUsersBalance(user1, account);
				if(transferbalance == 0) {
					System.out.println("This Account has no funds, please select a different account");
					//ensures there are no transfers from users without adequate funds
					validAccount = false;
				} else {
					boolean validtransfer = false;
					int transferamount = 0;
					while(validtransfer == false) {
						System.out.println("How much would you like to transfer?");
						transferamount = scanner.nextInt();
						if(transferamount > transferbalance) {
							System.out.println("Please select an amount less than the current account balance");
						}else if(transferamount <= 0) {
							System.out.println("Please select a value greater than 0 to transfer");
						}
						else {
							validtransfer = true;
						}
					}
					if(account.equals("checking")) {
						user1.setChecking(user1.getChecking() - transferamount);
						user1.setSavings(user1.getSavings() + transferamount);
					}else {
						user1.setSavings(user1.getSavings() + transferamount);
						user1.setChecking(user1.getChecking() - transferamount);
					}
					//ensures that the correct accounts are used to receive transfers
					System.out.println("Transfer Succuessful");
				}
			}
		}else {
			//this segment repeats the user account verification and transfer value steps for two user transfers
			while(validAccount == false) {
				System.out.println("Which account would you like to transfer from?\nChecking or Savings?");
				account = scanner.nextLine();
				if("checking".equals(account) || "savings".equals(account)) {
					validAccount = true;
				}else {
					System.out.println("Please select between Checking or savings");
				}
			}
			int transferbalance = getUsersBalance(user1, account);
			if(transferbalance == 0) {
				System.out.println("This Account has no funds, please select a different account");
				validAccount = false;
			} else {
				boolean validtransfer = false;
				int transferamount = 0;
				while(validtransfer == false) {
					System.out.println("How much would you like to transfer?");
					transferamount = scanner.nextInt();
					if(transferamount > transferbalance) {
						System.out.println("Please select an amount less than the current account balance");
					}else if(transferamount < 0) {
						System.out.println("Please select a value greater than 0 to transfer");
					}
					else {
						validtransfer = true;
					}
				}
				if(account.equals("checking")) {
					user1.setChecking(user1.getChecking() - transferamount);
					user2.setChecking(user2.getChecking() + transferamount);
				}else {
					user1.setSavings(user1.getSavings() - transferamount);
					user2.setSavings(user2.getSavings() + transferamount);
				}
				//ensures that the corresponding accounts receive adequate transfers
				System.out.println("Transfer Successful");
			}
		}
	}
}
