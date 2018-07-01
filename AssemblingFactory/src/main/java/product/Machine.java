package product;

/**
 * Written this class so that code is extendable for any new modification in product
 * 
 */
public class Machine {

	private String name;
	private String id;
	
	@Override
	public String toString() {
		return "Machine with id: " + id + " and name: " + name;
	}
	
	
}
