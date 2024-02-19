/**
 * This class stores the operations for the operator actions of the ATM
 */
public class OperatorMode{
	private Vault vault;
	
	/**
	 * @param vault the monetary contents of the ATM
	 */
	public OperatorMode(Vault vault) {
		this.vault = vault;
	}

	/**
	 * Displays the current amount of bills in the ATM by type
	 */
	public void displayBills() {
		vault.displayVaultStatus();
	}
	
	/**
	 * @param fives amount of $5 bills to add
	 * @param twenties amount of $20 bills to add
	 * @param fifties amount of $50 bills to add
	 * @param hundreds amount of $100 bills to add
	 */
	public void insertBills(int fives, int twenties, int fifties, int hundreds) {
		vault.setFiveDollarBills(vault.getFiveDollarBills() + fives);
		//this receives the current of bills in the vault and adds on top of it
		vault.setTwentyDollarBills(vault.getTwentyDollarBills() + twenties);
		vault.setFiftyDollarBills(vault.getFiftyDollarBills() + fifties);
		vault.setHundredDollarBills(vault.getHundredDollarBills() + hundreds);
	}
	
	/**
	 * @param fives amount of $5 bills to remove
	 * @param twenties amount of $20 bills to remove
	 * @param fifties amount of $50 bills to remove
	 * @param hundreds amount of $100 bills to remove
	 */
	public void removeBills(int fives, int twenties, int fifties, int hundreds) {
		vault.setFiveDollarBills(vault.getFiveDollarBills() - fives);
		//this receives the current of bills in the vault and subtracts from it
		vault.setTwentyDollarBills(vault.getTwentyDollarBills() - twenties);
		vault.setFiftyDollarBills(vault.getFiftyDollarBills() - fifties);
		vault.setHundredDollarBills(vault.getHundredDollarBills() - hundreds);
	}
}
