package com.techelevator;

public class Chip extends VendingItems implements Vendable {

	public Chip(String slotNumber, String name, int cost) {
		super(slotNumber, name, cost);
	}

	public void printChipMessage() {
		System.out.println("Crunch Crunch, Yum!");
	}
	
	public String getSlotNumber() {
		return super.getSlotNumber();
	}
	
	public void setSlotNumber(String slotNumber) {
		super.setSlotNumber(slotNumber);
	}
	
	public String getName() {
		return super.getName();
	}

	public void setName(String name) {
		super.setName(name);
	}
	
	public int getCost() {
		return super.getCost();
	}

	public void setCost(int cost) {
		super.setCost(cost);
	}

	@Override
	public int getQuantity() {
		return super.getQuantity();
	}
	
	public void setQuantity(int quantity) {
		super.setQuantity(quantity);
	}
}
