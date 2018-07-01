package inventory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import product.Bolt;
import product.Machine;

public class Warehouse {

	private BlockingQueue<Bolt> bolts; 
	private BlockingQueue<Machine> machines;
	
	public Warehouse(int totalBolts, int totalMachines) {
		if(totalBolts > 0)
			this.bolts = new ArrayBlockingQueue<>(totalBolts);
		if(totalMachines > 0)
			this.machines = new ArrayBlockingQueue<>(totalMachines);
		for(int i = 0; i< totalBolts; i++) {
			bolts.add(new Bolt());
		}
		for(int i = 0; i< totalMachines; i++) {
			machines.add(new Machine());
		}
	}
	
	public Bolt takeBolt() throws InterruptedException{
		if(bolts != null && !bolts.isEmpty()) {
			return bolts.take();
		} else {
			return null;
		}
	}
	
	public Machine takeMachine() throws InterruptedException {
		if(machines != null && !machines.isEmpty()) {
			return machines.take();
		} else {
			return null;
		}
	}
	
}
