package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendableItems {
	private static List<Vendable> vendableItemsList  = setVendablesList();
	
	public VendableItems() {}
	
	public static List<Vendable> getVendablesList(){
		return vendableItemsList;
	}
		
	private static List<Vendable> setVendablesList() {
		File inputFile = new File("vendingmachine.csv");//change this to change what items are vendable
		List<Vendable> resultList = new ArrayList<Vendable>();

		try (Scanner input = new Scanner(inputFile);) {
			VendingItem item;
			while (input.hasNextLine()) {
				String[] itemLine = input.nextLine().split("\\|");
				switch(itemLine[3]) {	
				
				case"Chip": item = new Chip(itemLine[0], itemLine[1], (Double.parseDouble(itemLine[2])));
				break;
				case"Candy": item = new Candy(itemLine[0], itemLine[1], (Double.parseDouble(itemLine[2])));
				break;
				case"Drink": item = new Drink(itemLine[0], itemLine[1], (Double.parseDouble(itemLine[2])));
				break;
				case"Gum": item = new Gum(itemLine[0], itemLine[1], (Double.parseDouble(itemLine[2])));
				break;
				default: item = new VendingItem("DEFAULT", "N/A", 0);
				break;
				}
				resultList.add(item);
			}
		}catch(FileNotFoundException e) {
			System.out.printf("Error: %s", e.getLocalizedMessage());
		}
		return resultList;	
	}
}
