package com.techelevator;

import java.util.List;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };
	
	private Menu menu;
	double currentMoney = 0;
	Scanner vendingScanner = new Scanner(System.in);
	
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) { 
				//here is the showing of the products
				for (Vendable item : VendableItems.getVendablesList()) {
					System.out.printf("---\nItem: %s   %s\nPrice: $%d\nQuantity Left: %d\n", 
							item.getSlotNumber(), item.getName(), item.getCost(), item.getQuantity());
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//here is the purchase stuff
				while (choice != PURCHASE_MENU_OPTION_FINISH_TRANSACTION) {
					System.out.println("---");
					System.out.printf("\nCurrent Money Provided: $%.2f", currentMoney);
					choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						System.out.println("Enter Money in Whole Dollar Amounts");
						System.out.println("Enter 0 to Stop Feeding Money");
						while (true) {
							System.out.print("\n$");
							String moneyInput = vendingScanner.nextLine();
							int moneyAmount = Integer.parseInt(moneyInput);
							if (moneyAmount == 0) {
								break;
							}
							currentMoney += moneyAmount;
							System.out.printf("Current Amount Provided: $%.2f", currentMoney);
						}
					} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						List<Vendable> instanceList = VendableItems.getVendablesList();
						for (Vendable item : instanceList) {
							if(item.getQuantity()>0) {
								System.out.printf("--%s   $%.2f\n%s\n", item.getSlotNumber(), item.getCost(), item.getName());
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
							itemObjectRequested.printMessage();
							currentMoney -= itemObjectRequested.getCost();
							itemObjectRequested.decrementQuantity();
							
							//this is where we would put the method to make a sales log file
						default:
							break;
						}								
					}
				}
				if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
					if (currentMoney > 0) {
						int change = (int)(Math.ceil(currentMoney*100));
					    int dollars = Math.round((int)change/100);
					    change %= 100;
					    int quarters = Math.round((int)change/25);
					    change %= 25;
					    int dimes = Math.round((int)change/10);
					    change %= 10;
					    int nickels = Math.round((int)change/5);
					    change %= 5;
					    int pennies = Math.round((int)change/1);
					    currentMoney = 0;
	
					    System.out.println("Dollars: " + dollars);
					    System.out.println("Quarters: " + quarters);
					    System.out.println("Dimes: " + dimes);
					    System.out.println("Nickels: " + nickels);
					    System.out.println("Pennies: " + pennies);
					    System.out.printf("Current Balance: $%.2f\n", currentMoney);
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Thank You For Your Patronage!");
				break;
			} // Add optional sales report option here
		}
	}

	public static void main(String[] args) {
		
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
