/**
 * This class stores the amount of money within the ATM
 */
public class Vault {

	private int FiveDollarBills = 100;
	private int FiftyDollarBills = 100;
	private int TwentyDollarBills = 100;
	private int HundredDollarBills = 100;
	//creates the starting amount of 100 for each type of bill
	
	private int totalamount = (FiveDollarBills * 5) + (TwentyDollarBills * 20) + (FiftyDollarBills * 50) + (HundredDollarBills * 100);
	//stores the current balance as a monetary value
	
	/**
	 * @return FiveDollarBills the amount of five dollars bills
	 */
	public int getFiveDollarBills() {
		return FiveDollarBills;
	}

	/**
	 * Sets the amount of five dollar bills in the system
	 * 
	 * @param FiveDollarBills
	 */
	public void setFiveDollarBills(int fiveDollarBills) {
		FiveDollarBills = fiveDollarBills;
	}

	/**
	 * @return FiftyDollarBills the amount of fifty dollars bills
	 */
	public int getFiftyDollarBills() {
		return FiftyDollarBills;
	}

	/**
	 * Sets the amount of fifty dollar bills in the system
	 * 
	 * @param FiftyDollarBills
	 */
	public void setFiftyDollarBills(int fiftyDollarBills) {
		FiftyDollarBills = fiftyDollarBills;
	}

	/**
	 * @return TwentyDollarBills the amount of twenty dollars bills
	 */
	public int getTwentyDollarBills() {
		return TwentyDollarBills;
	}

	/**
	 * Sets the amount of twenty dollar bills in the system
	 * 
	 * @param TwentyDollarBills
	 */
	public void setTwentyDollarBills(int twentyDollarBills) {
		TwentyDollarBills = twentyDollarBills;
	}

	/**
	 * @return HundredDollarBills the amount of hundred dollars bills
	 */
	public int getHundredDollarBills() {
		return HundredDollarBills;
	}

	/**
	 * Sets the amount of hundred dollar bills in the system
	 * 
	 * @param HundredDollarBills
	 */
	public void setHundredDollarBills(int hundredDollarBills) {
		HundredDollarBills = hundredDollarBills;
	}
	
	/**
	 * This method displays the current amount of each bill type 
	*/
	public void displayVaultStatus() {
		System.out.println("Amount of $5 bills: " + FiveDollarBills);
		System.out.println("Amount of $20 bills: " + TwentyDollarBills);
		System.out.println("Amount of $50 bills: " + FiftyDollarBills);
		System.out.println("Amount of $100 bills: " + HundredDollarBills);
	}
	
	/**
	 * This method displays the current balance of the vault
	 */
	public void displayVaultBalance() {
		System.out.println("Total Vault Balance: " + totalamount);
	}
	
	/**
	 * This method calculates if a monetary vale can be withdrawn from the current selection of bills
	 * 
	 * @param amount the amount of money to withdrawal from the vault
	 * @return a boolean that shows if the current value can be withdrawn
	 */
	public boolean withdrawalMath(int amount) {
		boolean changed = true;
		//checks to see if maximum amount of bills has been calculated
		while(amount != 0 && changed) {
			//this loop runs as long as the amount changes and is more than 0
			changed = false;
			if(amount >= 100 && HundredDollarBills > 0) {
				amount = amount - 100;
				//adjusts the amount for the appropriate monetary value
				HundredDollarBills = HundredDollarBills - 1;
				//adjusts the number of bills as appropriate
				changed = true;
			}else if(amount >= 50 && FiftyDollarBills > 0 ) {
				//section repeats for all available bill types
				amount = amount -50;
				FiftyDollarBills = FiftyDollarBills - 1;
				changed = true;
			}else if(amount >= 20 && TwentyDollarBills > 0) {
				amount = amount -20;
				TwentyDollarBills = TwentyDollarBills - 1;
				changed = true;
			}else if(amount >= 5 && FiveDollarBills > 0) {
				amount = amount -5;
				FiveDollarBills = FiveDollarBills - 1;
				changed = true;
			}
		}
		if(changed == false) {
			return false;
		}
		//returns false if the amount cannot be fully withdrawn
		return true;
		//if the full amount can be withdrawn 
	}
	
}
