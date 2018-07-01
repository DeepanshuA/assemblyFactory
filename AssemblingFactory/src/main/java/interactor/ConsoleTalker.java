package interactor;

import java.util.Scanner;

public class ConsoleTalker {

	private Scanner sc;
	
	public ConsoleTalker(Scanner scanner) {
		this.sc = scanner;
	}
	
	public InputTransferObject takeInputForProduction() {
	  	InputTransferObject inputTO = new InputTransferObject();
		
		System.out.println("Enter Number of bolts in warehouse");  
		Integer bolts = sc.nextInt();
		inputTO.setBolts(bolts);
		
		System.out.println("Enter Number of machines in warehouse");
		Integer machines = sc.nextInt();
		inputTO.setMachines(machines);
		
		System.out.println("Enter Number of time units (in sec) to assemble a product");
		Integer timeTaken = sc.nextInt();
		inputTO.setTimeTaken(timeTaken);
		return inputTO;
	}

}
