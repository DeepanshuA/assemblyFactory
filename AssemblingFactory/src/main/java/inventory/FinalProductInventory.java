package inventory;

import java.util.List;

import product.FinalProduct;

public class FinalProductInventory {

	private List<FinalProduct> finalProductList;
	
	public FinalProductInventory(List<FinalProduct> finalProductList){
		this.finalProductList = finalProductList;
	}
	
	public synchronized void addFinalProductToInventory(FinalProduct product) {
		finalProductList.add(product);
	}
	
	public synchronized int totalFinalProductProduced() {
		return finalProductList.size();
	}
	
}
