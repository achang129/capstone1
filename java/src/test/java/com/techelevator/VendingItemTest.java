package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class VendingItemTest {

	private VendingItem item = new VendingItem();
	
	@Test
	public void returns_proper_quantity_with_decrement() {
		item.decrementQuantity();
		item.decrementQuantity();
		Assert.assertEquals(3, item.getQuantity());
	}
	
	@Test
	public void returns_0_when_past_lowest_quantity() {
		item.decrementQuantity();
		item.decrementQuantity();
		item.decrementQuantity();
		item.decrementQuantity();
		item.decrementQuantity();
		item.decrementQuantity();
		Assert.assertEquals(0, item.getQuantity());
	}
}
