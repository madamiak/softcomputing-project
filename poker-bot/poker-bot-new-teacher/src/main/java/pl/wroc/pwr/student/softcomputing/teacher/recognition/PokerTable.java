package pl.wroc.pwr.student.softcomputing.teacher.recognition;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.Result;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Table;

public class PokerTable implements Table {

	private Result figures;
	private Result suits;
	private Result dealerPosition;

	@Override
	public void setFigures(Result figures) {
		this.figures = figures;
	}

	@Override
	public void setSuits(Result suits) {
		this.suits = suits;
	}

	@Override
	public void setDealerPosition(Result dealerPosition) {
		this.dealerPosition = dealerPosition;
	}

	@Override
	public String report() {
		StringBuilder sb = new StringBuilder();
		sb.append(figures.getResultAsString().charAt(0));
		sb.append(suits.getResultAsString().charAt(0));
		sb.append(" ");
		sb.append(figures.getResultAsString().charAt(1));
		sb.append(suits.getResultAsString().charAt(1));
		sb.append(" ");
		sb.append(dealerPosition.getResultAsString());
		return sb.toString();
	}

}
