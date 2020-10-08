package com.techelevator;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

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
	int currentMoney = 0;
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
					choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					System.out.println("\nCurrent Money Provided: $" + currentMoney);
					if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						System.out.println("Enter Money in Whole Dollar Amounts");
						System.out.println("Enter 0 to Stop Feeding Money");
						while (true) {
							System.out.print("$");
							String moneyInput = vendingScanner.nextLine();
							int moneyAmount = Integer.parseInt(moneyInput);
							if (moneyAmount == 0) {
								break;
							}
							currentMoney += moneyAmount;
							System.out.println("Current Amount Provided: $" + currentMoney);
						}
					} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						for (Vendable item : VendableItems.getVendablesList()) {
							System.out.printf("---\nItem: %s   %s\nPrice: $%d\nQuantity Left: %d\n", 
									item.getSlotNumber(), item.getName(), item.getCost(), item.getQuantity());
						}
//						System.out.println("Enter the code for the item you want: ");
//						String itemInput = vendingScanner.nextLine();
//						
//						if (!itemInput.contains(product.getSlotNumber())) {
//							System.out.println("That product code does not exist");
//						} else if (itemInput.contains(product.getSlotNumber()) && product.getQuantity() == 0) {
//							System.out.println("Sorry, but that item is sold out");
//						} else if (itemInput.contains(product.getSlotNumber()) && product.getCost() > currentMoney){
//							System.out.println("Sorry, but you do not have enough money for this product");
//						} else {
//							currentMoney -= product.getCost();
//						}
					}
				}
				if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
					if (currentMoney > 0) {
						int change = (int)(Math.ceil(currentMoney));
					    int dollars = Math.round((int)change/100);
					    change=change%100;
					    int quarters = Math.round((int)change/25);
					    change=change%25;
					    int dimes = Math.round((int)change/10);
					    change=change%10;
					    int nickels = Math.round((int)change/5);
					    change=change%5;
					    int pennies = Math.round((int)change/1);
					    currentMoney = 0;
	
					    System.out.println("Dollars: " + dollars);
					    System.out.println("Quarters: " + quarters);
					    System.out.println("Dimes: " + dimes);
					    System.out.println("Nickels: " + nickels);
					    System.out.println("Pennies: " + pennies);
					    System.out.println("Current Balance: " + currentMoney);
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Thank You For Your Patronage!");
				break;
			}
		}
	}

	public static void main(String[] args) {
		
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
	
//	public static void purchaseMenu() {
//		int currentMoney = 0;
//		System.out.println("(1) Feed Money");
//		System.out.println("(2) Select Produce");
//		System.out.println("(3) Finish Transaction");
//		System.out.println("\nCurrent Money Provided: $" + currentMoney);
//	}
}
