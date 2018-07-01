package inventory;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import product.FinalProduct;

public class FinalProductInventoryTest {

	private FinalProductInventory toTest;
	private List<FinalProduct> finalProductList;
	
	@Before
	public void setup() {
		finalProductList = new ArrayList<FinalProduct>();
		toTest = new FinalProductInventory(finalProductList);
	}
	
	@Test
	public void testAddingFinalProductToInventory() {
		FinalProduct prod = new FinalProduct();
		toTest.addFinalProductToInventory(prod);
		Assert.assertTrue(toTest.totalFinalProductProduced() == 1);
	}
	
}
