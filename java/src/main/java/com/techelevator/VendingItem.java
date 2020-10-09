package com.techelevator;

public class VendingItem implements Vendable{

	private String slotNumber;
	private String name;
	private double cost;
	private int quantity = 5;
	
	public VendingItem(String slotNumber, String name, double cost) {
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
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
