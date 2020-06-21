package a11715842.schneider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleCard implements Comparable<VehicleCard> {
	
	public enum Category {
		
		PRICE_EUR("Price", 1), 
		CYLINDER_CAPACITY_CM3("Hubraum", 5), 
		ENGINE_POWER_HP("Leistung", 4), 
		ACCELERATION_SEC("Beschleunigung", 3), 
		VELOCITY_KMH("Geschwindigkeit", 2), 
		CONSUMPTION_L("Verbrauch", 0) {
			@Override
			public int bonus(Double value) {
				return (int) (this.getFactor()+value);
			}
		};
		
		final private String categoryName;
		final private int factor;
		
		private Category(String categoryName, int factor) {
			if (categoryName == null) {
				throw new IllegalArgumentException("CategoryName is null.");
			}
			if (factor<0) {
				throw new IllegalArgumentException("Factor is less than zero.");
			}
			
			this.categoryName = categoryName;
			this.factor = factor;
			
		}
		
		public int bonus(Double value) {
			return (int) (this.factor*value);
		}
		
		public int getFactor() {
			return this.factor;
		}
		
		@Override
		public String toString() { 
			StringBuilder builder = new StringBuilder();
			builder.append(this.categoryName);
			return builder.toString();
		}
		
	}
	
	private String name;
	private Map<Category, Double> categories;
	
	public VehicleCard(String name, Map<Category, Double> categories) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("VehicleCard Name not okay.");
		}
		if (categories == null) {
			throw new IllegalArgumentException("Not every category exists in enum.");
		}
		for (Double c : categories.values()) {
			if (c == null || c < 0) {
				throw new IllegalArgumentException("Value in categories is null or below zero.");
			}
		}
		
		this.name = name;
		this.categories = categories;
	}
	
	public String getName() {
		return name;
	}
	
	public Map<Category, Double> getCategories() {
		return categories;
	}
	
	@Override
	public int compareTo(VehicleCard other) {
		return this.totalBonus() - other.totalBonus();
	}
	
	protected int getBonus(Category category) {
		return category.bonus(categories.get(category));
	}
	
	public int totalBonus(){
		int total = 0;
		
		for (Map.Entry<Category, Double> entry : categories.entrySet()) {
		    total += getBonus(entry.getKey());
		}

		return total;
	}
	
	public static Map<Category, Double> newCategoriesMap(double price, double capa, double pwr, double acc,
			double velo, double cons) {
		
		Map<Category, Double> newMap = new HashMap<Category, Double>();
		
		List<Category> categoryArray = Arrays.asList(Category.values());
		
		newMap.put(categoryArray.get(0), price);
		newMap.put(categoryArray.get(1), capa);
		newMap.put(categoryArray.get(2), pwr);
		newMap.put(categoryArray.get(3), acc);
		newMap.put(categoryArray.get(4), velo);
		newMap.put(categoryArray.get(5), cons);
		
		return newMap;
	}
	
	protected String categoryToString(Category category) {
		return category.toString();
	}
	
	@Override
	public String toString() {
		boolean first = true;	
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("- ");
		builder.append(this.name);
		builder.append("(");
		builder.append(totalBonus());
		builder.append(") -> {");
		
		for (Map.Entry<Category, Double> entry : categories.entrySet()) {
			if (first) {
				builder.append(categoryToString(entry.getKey()) + "=" + entry.getValue());
				first = false;
			}
			else {
				builder.append(", ");
				builder.append(categoryToString(entry.getKey()) + "=" + entry.getValue());
			}
		}
		
		builder.append("}");
		
		return builder.toString();
	}


}

	
