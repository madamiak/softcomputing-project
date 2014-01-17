package pl.wroc.pwr.student.softcomputing.teacher.recognition;

import java.util.HashMap;
import java.util.Map;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.Card;

public class PokerCard implements Card {
	private final Map<String, String> map = new HashMap<>();

	private final String figure;
	private final String suit;

	public PokerCard(char figure, char suit) {
		map.put("h", "Hearts");
		map.put("d", "Diamonds");
		map.put("c", "Clubs");
		map.put("s", "Spades");
		map.put("2", "2");
		map.put("3", "3");
		map.put("4", "4");
		map.put("5", "5");
		map.put("6", "6");
		map.put("7", "7");
		map.put("8", "8");
		map.put("9", "9");
		map.put("T", "10");
		map.put("J", "Joker");
		map.put("Q", "Queen");
		map.put("K", "King");
		map.put("A", "As");
		this.figure = String.valueOf(figure);
		this.suit = String.valueOf(suit);
	}
	
	@Override
	public String toString() {
		return map.get(figure) + " of " + map.get(suit);
	}

}
