package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineCLITest {

	private VendingMachineCLI vendingMachine = new VendingMachineCLI();
	
	@Test
	public void returns_false_for_improper_dollar_amount() {
		Assert.assertFalse("Expected false for improper amount 2.50", vendingMachine.isProperDollarAmount(2.50));
	}
	
	@Test
	public void returns_true_for_proper_dollar_amount_10() {
		Assert.assertTrue("Expected true for proper amount 10", vendingMachine.isProperDollarAmount(10));
	}
	
	@Test
	public void returns_false_for_near_dollar_amount() {
		Assert.assertFalse("Expected false for improper amount 0.99", vendingMachine.isProperDollarAmount(0.99));
	}
	
	@Test
	public void returns_false_for_unacceptable_whole_amount() {
		Assert.assertFalse("Expected false for dollar amount 20", vendingMachine.isProperDollarAmount(20));
	}
}
