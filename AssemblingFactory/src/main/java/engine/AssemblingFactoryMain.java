package engine;

import interactor.ConsoleTalker;
import interactor.InputTransferObject;

import java.util.Scanner;

public class AssemblingFactoryMain {

	private ConsoleTalker consoleTalker;
	private AssemblingFactoryEngine engine;
	
	public AssemblingFactoryMain(Scanner scanner) {
		this.consoleTalker = new ConsoleTalker(scanner);
		
		this.engine = new AssemblingFactoryEngine();
	}

	
	public static void main(String[] args) throws InterruptedException {

		Scanner sc = new Scanner(System.in);
		AssemblingFactoryMain assemblingFactoryMain = new AssemblingFactoryMain(sc);
		
		System.out.println("Lets Start producing stuff. Press enter to start");
		try {
			while (!sc.nextLine().equals("N")) {
				InputTransferObject inputTO = assemblingFactoryMain.consoleTalker.takeInputForProduction();
				assemblingFactoryMain.engine.setInputTO(inputTO);
				assemblingFactoryMain.engine.startProduce();
				System.out.println("Lets produce more");
			}
		} finally {
			sc.close();
		}
	
	}
}
