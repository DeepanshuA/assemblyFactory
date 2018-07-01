package assembler;

import java.util.concurrent.atomic.AtomicInteger;

import inventory.FinalProductInventory;
import product.Bolt;
import product.FinalProduct;
import product.Machine;

public class FinalProductProducer {

	private long timeTakenToProduceSingleUnit;
	private FinalProductInventory finalInventory;
	private AtomicInteger maxUnitsByOneWorker;
	
	public FinalProductProducer(long timeTakenToProduceSingleUnit,
			FinalProductInventory finalInventory) {
		this.timeTakenToProduceSingleUnit = timeTakenToProduceSingleUnit;
		this.finalInventory = finalInventory;
		maxUnitsByOneWorker = new AtomicInteger(0);
	}

	public void produce(Bolt[] bolts, Machine machine) throws InterruptedException {
		if(bolts.length == 2 && machine != null) {
			for(Bolt bolt: bolts) {
				if(bolt == null){
					return;
				}
			}
			System.out.println("Starting to produce final product now.");
			Thread.sleep(1000 * timeTakenToProduceSingleUnit);
			finalInventory.addFinalProductToInventory(new FinalProduct());
		}
	}
	
	public void setMaxUnitsByOneWorker(int maxFromAThread) {
		maxUnitsByOneWorker.getAndAccumulate(maxFromAThread, Math::max);
	}
	
	public int getMaxUnitsByOneWorker() {
		return maxUnitsByOneWorker.get();
	}
	
}
