package a11715842.schneider;

import java.util.List;
import java.util.Map;

public class FoilVehicleCard extends VehicleCard {
	
	private List<Category> specials;

	public FoilVehicleCard(String name, Map<Category, Double> categories,List<Category> specials) {
		super(name,categories);
		if (specials == null || specials.size() > 3) {
			throw new IllegalArgumentException ("Special is either null or contains more than 3.");
		}
		this.specials = specials;
	}
	
	@Override
	protected int getBonus(Category category) {
		if (specials.contains(category))
			return 2*super.getBonus(category);
		else
			return super.getBonus(category);
	}
	
	@Override
	protected String categoryToString(Category category) {
		for (Category c : specials) {
			if (c.equals(category))
				return "*" + category.toString() + "*";
		}
		return category.toString();
	}
	
}
