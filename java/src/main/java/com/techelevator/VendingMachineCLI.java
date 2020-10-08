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
				
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				purchaseMenu();
				
			}
		}
	}

	public static void main(String[] args) {
		try {
		for (Vendable item : getVendablesToDisplay()) {
			System.out.println(item.getSlotNumber());
		}
		}catch(FileNotFoundException e) {
			//nom nom error
		}
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
	
	public static List<Vendable> getVendablesToDisplay() throws FileNotFoundException {
		File inputFile = new File("vendingmachine.csv");
		List<Vendable> resultList = new ArrayList<Vendable>();

		try (Scanner input = new Scanner(inputFile);) {
			VendingItems item;
			while (input.hasNextLine()) {
				String[] itemLine = input.nextLine().split("\\|");
				switch(itemLine[3]) {	
				
				case"Chip": item = new Chip(itemLine[0], itemLine[1], (int) (Double.parseDouble(itemLine[2])*100));
				break;
				case"Candy": item = new Candy(itemLine[0], itemLine[1], (int) (Double.parseDouble(itemLine[2])*100));
				break;
				case"Drink": item = new Drink(itemLine[0], itemLine[1], (int) (Double.parseDouble(itemLine[2])*100));
				break;
				case"Gum": item = new Gum(itemLine[0], itemLine[1], (int) (Double.parseDouble(itemLine[2])*100));
				break;
				default: item = new VendingItems("DEFAULT", "N/A", 0);
				break;
				}
				resultList.add(item);
			}
		}
		return resultList;	
	}
	
	public static void purchaseMenu() {
		int currentMoney = 0;
		System.out.println("(1) Feed Money");
		System.out.println("(2) Select Produce");
		System.out.println("(3) Finish Transaction");
		System.out.println("\nCurrent Money Provided: $" + currentMoney);
	}
}
