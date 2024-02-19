/**
 * This class starts and initializes the entire program
 */
public class Main {
	
	/**
	 * this starts the initialization of the users and the ATM itself
	 * 
	 * @param args the arguments which affect input (not utilized in this program)
	 */
	public static void main(String[] args) {
		Bank bank = InitializeUsers();
		InitializeATM(bank);
	}
	
	/**
	 * this method runs and starts the ATM itself
	 * 
	 * @param bank the current user database system
	 */
	public static void InitializeATM(Bank bank) {
		ATMSystem atm = new ATMSystem(bank);
	}
	
	/**
	 * @return Bank a bank object which holds the current user database of the ATM
	 */
	public static Bank InitializeUsers() {
		return new Bank();
	}

}
