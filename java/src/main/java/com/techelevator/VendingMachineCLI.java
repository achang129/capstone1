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
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };

	private Menu menu;
	
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) { 
				//here is the showing of the products
				for (Vendable item : VendableItems.getVendablesList()) {
					System.out.printf("---\nItem: %s   %s\nPrice: $%d\nQuantity Left: %d", 
							item.getSlotNumber(), item.getName(), item.getCost(), item.getQuantity());
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//here is the purchase stuff				
			}
		}
	}

	public static void main(String[] args) {
		
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
	
	public static void purchaseMenu() {
		int currentMoney = 0;
		System.out.println("(1) Feed Money");
		System.out.println("(2) Select Produce");
		System.out.println("(3) Finish Transaction");
		System.out.println("\nCurrent Money Provided: $" + currentMoney);
	}
}
