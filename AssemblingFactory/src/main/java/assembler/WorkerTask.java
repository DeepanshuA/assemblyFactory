package assembler;

import inventory.Warehouse;

import java.util.concurrent.CountDownLatch;

import product.Bolt;
import product.Machine;

public class WorkerTask implements Runnable {

	private Warehouse warehouse;
	private Bolt[] bolts;
	private int boltsFetched;
	private Machine machine;
	private FinalProductProducer finalProductProducer;
	private boolean boltsFinished; 
	private boolean machineFinished;
	private CountDownLatch latch;
	private int myMax;
	
	public WorkerTask(Warehouse warehouse, FinalProductProducer finalProductProducer, CountDownLatch latch) {
		this.warehouse = warehouse;
		bolts = new Bolt[2];
		this.finalProductProducer = finalProductProducer;
		this.latch = latch;
	}
	
	private void pickBolt() throws InterruptedException {
		if(boltsFetched < 2) {
			Bolt bolt = warehouse.takeBolt();
			if(bolt != null) {
				bolts[boltsFetched] = bolt;
				boltsFetched++;
			} else {
				boltsFinished = true;
			}
		}
	}
	
	private void pickMachine() throws InterruptedException {
		if(machine == null) {
			Machine tempMachine = warehouse.takeMachine();
			if(tempMachine != null) {
				machine = tempMachine;
			} else {
				machineFinished = true;
			}
		}
	}
	
	private void takeInputIfNotEnoughAndProduce() throws InterruptedException {
		if(boltsFetched < 2 || machine ==  null) {
			while(boltsFetched < 2 && !boltsFinished) {
				pickBolt();
			}
			if(machine == null && !machineFinished) {
				pickMachine();
			}
		}
		if(!boltsFinished && !machineFinished){
			produce(bolts, machine);
			myMax++;
			finalProductProducer.setMaxUnitsByOneWorker(myMax);
		}
		boltsFetched = 0;
		bolts = new Bolt[2];
		machine = null;
	}
	
	void produce(Bolt[] bolts, Machine machine) throws InterruptedException {
		finalProductProducer.produce(bolts, machine);
	}

	@Override
	public void run() {
		try {
			while(!boltsFinished || !machineFinished) {
				takeInputIfNotEnoughAndProduce();
			}
			latch.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
