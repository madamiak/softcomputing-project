package pl.wroc.pwr.student.softcomputing.teacher.recognition;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.Border;

public class PokerBorder implements Border {

	private String label;

	public PokerBorder(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		return label;
	}

}
