package engine;

import interactor.InputTransferObject;
import inventory.FinalProductInventory;
import inventory.Warehouse;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import product.FinalProduct;
import assembler.FinalProductProducer;
import assembler.WorkerTask;

public class AssemblingFactoryEngine {

	private InputTransferObject inputTO;
	
	public void startProduce() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(3);
		Warehouse warehouse = new Warehouse(inputTO.getBolts(), inputTO.getMachines());
		FinalProductInventory inventory = new FinalProductInventory(new ArrayList<FinalProduct>());
		FinalProductProducer producer = new FinalProductProducer(
				inputTO.getTimeTaken(), inventory);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for(int i =0; i< 3; i++) {
			executor.execute(new WorkerTask(warehouse, producer, latch));
		}
		latch.await();
		System.out.println("Total number of products produced: " + inventory.totalFinalProductProduced());
		System.out.println("Total tome taken: " + producer.getMaxUnitsByOneWorker() * inputTO.getTimeTaken());
	}

	public void setInputTO(InputTransferObject inputTO) {
		this.inputTO = inputTO;
	}
	
}
