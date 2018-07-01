package product;

/**
 * Written this class so that code is extendable for any new modification in product
 * 
 */
public class Bolt {

	private String name;
	private String id;
	
	@Override
	public String toString() {
		return "Bolt with id: " + id + " and name: " + name;
	}
	
}
