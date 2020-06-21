package a11715842.schneider;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import a11715842.schneider.VehicleCard.Category;

import java.util.List;

public class main {

	public static void main(String[] args) {
		
		// Aufgabe 1
		
		Map<VehicleCard.Category, Double> peugeotMap = new HashMap<VehicleCard.Category, Double>();
		VehicleCard peugeot = null;
		try {
//			peugeotMap.put(VehicleCard.Category.PRICE_EUR, 22600.);
//			peugeotMap.put(VehicleCard.Category.CYLINDER_CAPACITY_CM3, 1899.);
//			peugeotMap.put(VehicleCard.Category.ENGINE_POWER_HP, 186.);
//			peugeotMap.put(VehicleCard.Category.ACCELERATION_SEC, 7.9);
//			peugeotMap.put(VehicleCard.Category.VELOCITY_KMH, 205.);
//			peugeotMap.put(VehicleCard.Category.CONSUMPTION_L, 8.5);
//			
//			peugeot = new VehicleCard("Peugeout Rifter", peugeotMap);
//			System.out.println(peugeot);
			peugeot = new VehicleCard("Peugeot Rifter", VehicleCard.newCategoriesMap(22600.,1899.,186.,7.9,205.,8.5));
			System.out.println(peugeot);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		
		Map<VehicleCard.Category, Double> vwMap = new HashMap<VehicleCard.Category, Double>();
		VehicleCard vw = null;
		try {
			vwMap.put(VehicleCard.Category.PRICE_EUR, 15400.);
			vwMap.put(VehicleCard.Category.CYLINDER_CAPACITY_CM3, 1700.);
			vwMap.put(VehicleCard.Category.ENGINE_POWER_HP, 160.);
			vwMap.put(VehicleCard.Category.ACCELERATION_SEC, 4.7);
			vwMap.put(VehicleCard.Category.VELOCITY_KMH, 220.);
			vwMap.put(VehicleCard.Category.CONSUMPTION_L, 5.7);
			
			vw = new VehicleCard("VW Golf", vwMap);
			System.out.println(vw);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		
		Map<VehicleCard.Category, Double> bmwMap = new HashMap<VehicleCard.Category, Double>();
		FoilVehicleCard bmw = null;
		
		try {
			bmwMap.put(VehicleCard.Category.PRICE_EUR, 120000.);
			bmwMap.put(VehicleCard.Category.CYLINDER_CAPACITY_CM3, 2500.);
			bmwMap.put(VehicleCard.Category.ENGINE_POWER_HP, 200.);
			bmwMap.put(VehicleCard.Category.ACCELERATION_SEC, 6.8);
			bmwMap.put(VehicleCard.Category.VELOCITY_KMH, 250.);
			bmwMap.put(VehicleCard.Category.CONSUMPTION_L, 9.4);
			
			List<Category> bmwSpecials = new ArrayList<Category>();
			bmwSpecials.add(Category.CONSUMPTION_L);
		
			
			bmw = new FoilVehicleCard("BMW G11", bmwMap, bmwSpecials);
			System.out.println(bmw);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e);
		}
	
		
		Map<VehicleCard.Category, Double> mercedesMap = new HashMap<VehicleCard.Category, Double>();
		VehicleCard mercedes = null;
		try {
			mercedesMap.put(VehicleCard.Category.PRICE_EUR, 130000.);
			mercedesMap.put(VehicleCard.Category.CYLINDER_CAPACITY_CM3, 2700.);
			mercedesMap.put(VehicleCard.Category.ENGINE_POWER_HP, 0.);
			mercedesMap.put(VehicleCard.Category.ACCELERATION_SEC, 7.2);
			mercedesMap.put(VehicleCard.Category.VELOCITY_KMH, 240.);
			mercedesMap.put(VehicleCard.Category.CONSUMPTION_L, -2.);
			
			mercedes = new VehicleCard("Mercedes S", mercedesMap);
			System.out.println(mercedes);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		
		Map<VehicleCard.Category, Double> astonMap = new HashMap<VehicleCard.Category, Double>();
		FoilVehicleCard aston = null;
		try {
			astonMap.put(VehicleCard.Category.PRICE_EUR, 590000.);
			astonMap.put(VehicleCard.Category.CYLINDER_CAPACITY_CM3, 3995.);
			astonMap.put(VehicleCard.Category.ENGINE_POWER_HP, 286.);
			astonMap.put(VehicleCard.Category.ACCELERATION_SEC, 7.1);
			astonMap.put(VehicleCard.Category.VELOCITY_KMH, 229.);
			astonMap.put(VehicleCard.Category.CONSUMPTION_L, 10.4);
			
			List<Category> astonSpecials = new ArrayList<Category>();
			astonSpecials.add(Category.PRICE_EUR);
			astonSpecials.add(Category.ENGINE_POWER_HP);
			astonSpecials.add(Category.VELOCITY_KMH);
			
			
			aston = new FoilVehicleCard("Aston Martin DB5", astonMap, astonSpecials);
			System.out.println(aston);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		
		Player anakin = null;
		
		try {
			anakin = new Player("Anakin");
			anakin.addCard(bmw);
			anakin.addCard(peugeot);
			
			System.out.println(anakin);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		
		Player obi = null;
		
		try {
			obi = new Player("Obi-Wan");
			obi.addCard(bmw);
			obi.addCard(bmw);
			obi.addCard(aston);
			
			System.out.println(obi);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		
		System.out.println(anakin.challengePlayer(obi));
		
		Player quigon = null;
		
		try {
			quigon = new Player("Qui-Gon");
			quigon.addCard(vw);
			quigon.addCard(vw);
			
			System.out.println(quigon);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		
		System.out.println(quigon.challengePlayer(anakin));
		
TreeSet<Player> players = new TreeSet<Player>(Player.compareByBonus());
		
		players.add(anakin);
		players.add(obi);
		players.add(quigon);
		
		System.out.println(players);
		
		
		
	}

}
