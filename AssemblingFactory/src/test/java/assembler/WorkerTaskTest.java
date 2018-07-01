package assembler;

import inventory.Warehouse;

import java.util.concurrent.CountDownLatch;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import product.Bolt;
import product.Machine;

public class WorkerTaskTest {

	private Warehouse warehouse;
	@Mock
	private FinalProductProducer finalProductProducer;
	private CountDownLatch latch;
	private WorkerTask toTest;
	
	@Before
	public void setup() {
		finalProductProducer = Mockito.mock(FinalProductProducer.class);
		latch = new CountDownLatch(1);
	}
	
	@Test
	public void testPickingAndProducingWhenBoltsAndMachineAvailable() throws InterruptedException {
		warehouse = new Warehouse(2, 1);
		toTest = new WorkerTask(warehouse, finalProductProducer, latch);
		toTest.run();
		Mockito.verify(finalProductProducer, Mockito.times(1)).produce(Mockito.any(Bolt[].class), Mockito.any(Machine.class));
	}
	
	@Test
	public void testPickingAndProducingWhen6BoltsAnd2MachineAvailable() throws InterruptedException {
		warehouse = new Warehouse(6, 2);
		toTest = new WorkerTask(warehouse, finalProductProducer, latch);
		toTest.run();
		Mockito.verify(finalProductProducer, Mockito.times(2)).produce(Mockito.any(Bolt[].class), Mockito.any(Machine.class));
	}
	
	@Test
	public void testPickingButNotProducingWhenBoltsAndMachineNotAvailable() throws InterruptedException {
		warehouse = new Warehouse(1, 1);
		toTest = new WorkerTask(warehouse, finalProductProducer, latch);
		toTest.run();
		Mockito.verify(finalProductProducer, Mockito.times(0)).produce(Mockito.any(Bolt[].class), Mockito.any(Machine.class));
	}
	
}
