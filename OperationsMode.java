/**
 * This class switches the actions from the ATM between the Customer facing operations and the Operator facing Operations
 */
import java.util.Scanner;
public class OperationsMode{

	private CustomerMode cmmode;
	private OperatorMode ommode;
	private User currentUser;
	
	/**
	 * @param currentUser stores the currentUser to verify if the current user is an operator
	 */
	public OperationsMode(User currentUser) {
		cmmode = null;
		ommode = null;
		this.currentUser = currentUser;
	}
	
	/**
	 * This method will allow the admin to flip between customer and operator modes,
	 * if any other account uses it, will automatically go to customer mode
	 * 
	 * @param vault the current contents of the ATM
	 * @param users the current database of users in the bank
	 */
	public void SwitchtoOpMode(Vault vault, UserDatabase users) {
		Scanner scanner = new Scanner(System.in);
		String currentusrname = currentUser.getUsername();
		if(currentusrname.equals("admin")) {
			//tests to see if the current user is an operator
			System.out.println("Welcome Operator!\nWould you like to view the Customer view or the Operator view?");
			boolean trueview =  false;
			String view = null;
			while(trueview == false) {
				view = scanner.nextLine();
				//allows the operator to type which mode they want to use
				if(view.equals("operator")) {
					System.out.println("Operator Mode selected");
					ommode = new OperatorMode(vault);
					trueview = true;
				}else if(view.equals("customer")) {
					System.out.println("Customer Mode selected");
					cmmode = new CustomerMode(users, currentUser);
					trueview = true;
				}else {
					System.out.println("Please select a valid mode");
				}
			}
		}else {
			cmmode = new CustomerMode(users, currentUser);
			//automatically selected if the user is not an operator
		}
	}

	/**
	 * @return cmmode The CustomerMode command list
	 */
	public CustomerMode getCmmode() {
		return cmmode;
	}

	/**
	 * @return ommode The OperatorMode command list
	 */
	public OperatorMode getOmmode() {
		return ommode;
	}
	
	
}
