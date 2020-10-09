package com.techelevator;

public class Chip extends VendingItem implements Vendable {

	public Chip(String slotNumber, String name, double cost) {
		super(slotNumber, name, cost);
	}

	public void printMessage() {
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
	
	public double getCost() {
		return super.getCost();
	}

	public void setCost(double cost) {
		super.setCost(cost);
	}

	@Override
	public int getQuantity() {
		return super.getQuantity();
	}
	
	public void decrementQuantity() {
		super.decrementQuantity();
	}
}
