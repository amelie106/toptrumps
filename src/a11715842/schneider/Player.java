package a11715842.schneider;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Queue;

public class Player implements Comparable<Player> {
	

	private String name;
	private Queue<VehicleCard> deck = new ArrayDeque<VehicleCard>();

	public Player(String name) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException ("Name of Player invalid.");
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addCards(Collection<VehicleCard> cards) {
		deck.addAll(cards);
	}
	
	public void addCard(VehicleCard card) {
		deck.add(card);
	}
	
	public void clearDeck() {
		deck.clear();
	}
	
	public VehicleCard playNextCard() {
		return deck.poll();
	}
	
	
	@Override
	public int compareTo(Player other) {
		return this.name.compareToIgnoreCase(other.name);
	}

	@Override
	public int hashCode() {
		String nameLower = name.toLowerCase();
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameLower == null) ? 0 : nameLower.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		Player other = (Player) obj;
		
		String thisLower = this.name.toLowerCase();
		String otherLower = other.name.toLowerCase();
		
		if (this == obj)
			return true;
		if (!(obj instanceof Player))
			return false;
		if (name == null) {
			if (otherLower != null)
				return false;
		} else if (!thisLower.equals(otherLower))
			return false;
		return true;
	}
	
	public boolean challengePlayer(Player p) {
		
		if (p == null || p.equals(this)) 
			throw new IllegalArgumentException ("Challenge failed --> p invald.");
		
		Collection<VehicleCard> thisPlayed = new ArrayList<VehicleCard>();
		Collection<VehicleCard> pPlayed = new ArrayList<VehicleCard>();
		
		while (!(this.deck.isEmpty()) && !(p.deck.isEmpty())) {	
			
			VehicleCard thisCard = this.playNextCard();
			VehicleCard pCard = p.playNextCard();
			
			thisPlayed.add(thisCard);
			pPlayed.add(pCard);
			
			int thisTotal = thisCard.totalBonus();
			int pTotal = pCard.totalBonus();
			
			if (thisTotal < pTotal) {
				p.addCards(thisPlayed);
				p.addCards(pPlayed);
				return true;
				
			}
			else if (thisTotal > pTotal) {
				this.addCards(thisPlayed);
				this.addCards(pPlayed);
				return false;
			}
		}
		
		this.addCards(thisPlayed);
		p.addCards(pPlayed);
		
		return false;
	}
	
	public static Comparator<Player> compareByBonus() {
		return (a,b) -> {
			
			int aTotal = 0;
			int bTotal = 0;
			
			for (VehicleCard card : a.deck) {
				aTotal += card.totalBonus();
			}
			
			for (VehicleCard card : b.deck) {
				bTotal += card.totalBonus();
			}
			
			return aTotal-bTotal;
		};
		
	}
	
	public static Comparator<Player> compareByDeckSize() {
		return (a,b) -> {
			return a.deck.size() - b.deck.size();
		};	
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append("(");
		
		int aTotal = 0;
		for (VehicleCard card : deck) {
			aTotal += card.totalBonus();
		}
		
		builder.append(aTotal);
		builder.append("):\n");
		
		boolean first = true;
		for (VehicleCard card : deck) {
			if (first) {
				builder.append(card);
				first = false;
			}
			else {
				builder.append("\n"+card);
			}
		}
		
		return builder.toString();
	}
	
	
}