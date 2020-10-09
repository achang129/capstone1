package com.techelevator;

public interface Vendable {

	public String getSlotNumber();
	public String getName();
	public double getCost();
	public int getQuantity();
	public void decrementQuantity();
	public void printMessage();
}
