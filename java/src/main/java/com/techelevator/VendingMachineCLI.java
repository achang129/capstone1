package com.techelevator;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {
	//press 4 in first menu to initiate log mode, shows log for any one day.
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_GET_LOG = "";
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_GET_LOG };
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };

	private Menu menu;
	double currentMoney = 0;
	Scanner vendingScanner = new Scanner(System.in);
	Logger vendingLogger = new Logger();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	
	public VendingMachineCLI() {
		
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				//here is the showing of the products
				for (Vendable item : VendableItems.getVendablesList()) {
					System.out.printf("---\nItem: %s   %s\nPrice: $%.2f\nQuantity Left: %d\n",
							item.getSlotNumber(), item.getName(), item.getCost(), item.getQuantity());
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//here is the purchase stuff
				while (choice != PURCHASE_MENU_OPTION_FINISH_TRANSACTION) {
					System.out.println("---");
					System.out.printf("\nCurrent Money Provided: $%.2f", currentMoney);
					choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						feedMoney();
					} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						purchase();
					}
				}
				if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
					cashout();
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Thank You For Your Patronage!");
				break;
			} else if (choice.equals(MAIN_MENU_OPTION_GET_LOG)) {
				// Add optional sales report functionality here
				System.out.println("Enter the Year you would like to see the log of: YYYY");
				String year = vendingScanner.nextLine();
				System.out.println("Enter the Month you would like to see the log of: MM");
				String month = vendingScanner.nextLine();
				System.out.println("Enter the Day you would like to see the log of: DD");
				String day = vendingScanner.nextLine();
				vendingLogger.printDayofLog(year, month, day);
			}
		}
	}
	
	public void feedMoney() {
		System.out.println("\nEnter Money in Whole Dollar Amounts");
		System.out.println("-- $1, $2, $5, or $10 --");
		System.out.println("Enter $0 to Stop Feeding Money");
		while (true) {
			System.out.print("\n$");
			String moneyInput = vendingScanner.nextLine();
			double moneyAmount = 0;
			try{
				moneyAmount = Double.parseDouble(moneyInput);
				if (!isProperDollarAmount(moneyAmount)) {
					moneyAmount = 0;
				}
			}catch(NumberFormatException e) {
				System.out.printf("Error: %s\nReturning Money and Restarting\n", e.getLocalizedMessage());
				cashout();
				run();
			}
			if (moneyAmount <= 0) {
				break;
			}
			double priorMoney = currentMoney;
			currentMoney += moneyAmount;
			try {
				vendingLogger.logFeed(priorMoney, currentMoney);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			System.out.printf("Current Amount Provided: $%.2f", currentMoney);
		}
	}

	public void purchase() {
		List<Vendable> instanceList = VendableItems.getVendablesList();
		for (Vendable item : instanceList) {
			if(item.getQuantity()>=0) {
				System.out.printf("--%s   $%.2f\n%s | %d Left\n", item.getSlotNumber(), item.getCost(), item.getName(), item.getQuantity());
			}
		}
		System.out.println("Enter the slot code for the snack you would like: ");
		String userRequestedSlot = vendingScanner.nextLine();
		int canPurchase = 0;
		Vendable itemObjectRequested = null;

		for (int i = 0; i < instanceList.size(); i++) {
			if(instanceList.get(i).getSlotNumber().equalsIgnoreCase(userRequestedSlot)) {
				canPurchase += 1;
				if(instanceList.get(i).getQuantity()>0) {
					canPurchase +=1;
					if(instanceList.get(i).getCost()<=currentMoney) {
						canPurchase+=1;
						itemObjectRequested = instanceList.get(i);
					}
				}
			}
		}

		switch (canPurchase) {
		case 0:
			System.out.println("No item exists in requested slot.");
			break;
		case 1:
			System.out.println("None of requested item in stock.");
			break;
		case 2:
			System.out.println("Not enough funds for item.");
			break;
		case 3:
			System.out.printf("\nDispensing %s for $%.2f\n", itemObjectRequested.getName(), itemObjectRequested.getCost());
			itemObjectRequested.printMessage();
			double beforePayment = currentMoney;
			currentMoney -= itemObjectRequested.getCost();
			itemObjectRequested.decrementQuantity();

			try {
				vendingLogger.logPurchase(itemObjectRequested.getName(), itemObjectRequested.getSlotNumber(), beforePayment, currentMoney);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		default:
			break;
		}
	}


	public void cashout() {//this is used to either cash out normally or to force cashout if error occurs so user does not lose their money
		if (currentMoney > 0) {
			double changeMoney = currentMoney;
			int change = (int)(Math.ceil(currentMoney*100));
		    int dollars = Math.round((int)change/100);
		    change %= 100;
		    int quarters = Math.round((int)change/25);
		    change %= 25;
		    int dimes = Math.round((int)change/10);
		    change %= 10;
		    int nickels = Math.round((int)change/5);
		    change %= 5;
		    currentMoney = 0;

		    System.out.println("Dollars: " + dollars);
		    System.out.println("Quarters: " + quarters);
		    System.out.println("Dimes: " + dimes);
		    System.out.println("Nickels: " + nickels);
		    System.out.printf("Current Balance: $%.2f\n", currentMoney);
		    try {
				vendingLogger.logChange(changeMoney, currentMoney);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isProperDollarAmount(double moneyAmount) {
		if (moneyAmount == 1 || moneyAmount == 2 || moneyAmount == 5 || moneyAmount == 10 || moneyAmount == 0) {
			return true;
		} else {
			System.out.println("Error: Invalid Dollar Amount");
			return false;
		}
	}

	public static void main(String[] args) {

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();

	}
}
