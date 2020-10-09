package com.techelevator;

public class Gum extends VendingItem implements Vendable {

	public Gum(String slotNumber, String name, int cost) {
		super(slotNumber, name, cost);
	}
	
	public void printMessage() {
		System.out.println("Chew Chew, Yum!");
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
	
	public void decrementQuantity() {
		super.decrementQuantity();
	}
}
