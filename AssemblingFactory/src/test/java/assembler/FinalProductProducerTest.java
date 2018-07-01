package assembler;

import inventory.FinalProductInventory;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import product.Bolt;
import product.FinalProduct;
import product.Machine;

public class FinalProductProducerTest {

	@Mock
	private FinalProductInventory mockInventory;
	private FinalProductProducer toTest;
	List<FinalProduct> finalProductList;
	
	@Before
	public void setup() {
		finalProductList = new ArrayList<FinalProduct>();
		mockInventory = new FinalProductInventory(finalProductList);
		toTest = new FinalProductProducer(1, mockInventory);
	}
	
	@Test
	public void testSuccessfulProduceWhenBoltsAndMachinesPresent() throws InterruptedException {
		Bolt[] bolts = new Bolt[2];
		bolts[0] = new Bolt();
		bolts[1] = new Bolt();
		Machine machine = new Machine();
		toTest.produce(bolts, machine);
		Assert.assertTrue(finalProductList.size() == 1);
	}
	
	@Test
	public void testProductNotProducedWhenBoltsNotPresent() throws InterruptedException {
		Bolt[] bolts = new Bolt[2];
		Machine machine = new Machine();
		toTest.produce(bolts, machine);
		Assert.assertTrue(finalProductList.size() == 0);
	}
	
	@Test
	public void testProductNotProducedWhenMachineNotPresent() throws InterruptedException {
		Bolt[] bolts = new Bolt[2];
		bolts[0] = new Bolt();
		bolts[1] = new Bolt();
		Machine machine = null;
		toTest.produce(bolts, machine);
		Assert.assertTrue(finalProductList.size() == 0);
	}
	
	@Test
	public void testMaximumUnitsByOneWorkerCorrectly() {
		toTest.setMaxUnitsByOneWorker(2);
		toTest.setMaxUnitsByOneWorker(1);
		Assert.assertTrue(toTest.getMaxUnitsByOneWorker() == 2);
	}
	
}
