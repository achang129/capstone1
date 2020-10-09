package com.techelevator;

public class VendingItem implements Vendable{

	private String slotNumber;
	private String name;
	private int cost;
	private int quantity = 5;
	
	public VendingItem(String slotNumber, String name, int cost) {
		this.slotNumber = slotNumber;
		this.name = name;
		this.cost = cost;
	}

	public String getSlotNumber() {
		return slotNumber;
	}
	
	public void setSlotNumber(String slotNumber) {
		this.slotNumber = slotNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getQuantity() {
		return quantity;
	}
	public void decrementQuantity() {
		this.quantity--;
	}
	public void printMessage() {
		System.out.println("Yum!");
	}
}
