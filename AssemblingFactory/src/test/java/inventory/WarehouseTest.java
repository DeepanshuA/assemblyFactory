package inventory;

import org.junit.Assert;
import org.junit.Test;

import product.Bolt;
import product.Machine;

public class WarehouseTest {

	private Warehouse warehouse;
	
	@Test
	public void testBoltTaking() throws InterruptedException {
		warehouse = new Warehouse(2, 1);
		Bolt bolt = warehouse.takeBolt();
		Assert.assertTrue(bolt!= null);
	}
	
	@Test
	public void testWhenNoBolt() throws InterruptedException {
		warehouse = new Warehouse(0, 1);
		Bolt bolt = warehouse.takeBolt();
		Assert.assertTrue(bolt== null);
	}
	
	@Test
	public void testMachineTaking() throws InterruptedException {
		warehouse = new Warehouse(2, 1);
		Machine machine = warehouse.takeMachine();
		Assert.assertTrue(machine != null);
	}
	
	@Test
	public void testWhenNoMachine() throws InterruptedException {
		warehouse = new Warehouse(10, 0);
		Machine machine = warehouse.takeMachine();
		Assert.assertTrue(machine == null);
	}
	
}
