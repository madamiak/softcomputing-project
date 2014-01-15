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
		this.figure = String.valueOf(figure);
		this.suit = String.valueOf(suit);
	}
	
	@Override
	public String toString() {
		String string = !figure.equals("T") ? figure : "10";
		return string + " of " + map.get(suit);
	}

}
