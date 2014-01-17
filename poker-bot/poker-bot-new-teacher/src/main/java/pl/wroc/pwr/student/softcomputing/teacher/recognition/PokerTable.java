package pl.wroc.pwr.student.softcomputing.teacher.recognition;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.Border;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Card;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Fold;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Result;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Table;

public class PokerTable implements Table {

	private Result figures;
	private Result suits;
	private Result dealerPosition;
	private Result chips;
	private Result tableChips;
	private Result borders;
	private Result foldButton;

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
	public void setFoldButton(Result foldButton) {
		this.foldButton = foldButton;
	}

	@Override
	public void setChips(Result chips) {
		this.chips = chips;
	}

	@Override
	public void setTableChips(Result tableChips) {
		this.tableChips = tableChips;
	}

	@Override
	public void setBorders(Result borders) {
		this.borders = borders;
	}

	@Override
	public Card getFirstCard() {
		return new PokerCard(figures.getResultAsString().charAt(0), suits
				.getResultAsString().charAt(0));
	}

	@Override
	public Card getSecondCard() {
		return new PokerCard(figures.getResultAsString().charAt(1), suits
				.getResultAsString().charAt(1));
	}

	@Override
	public int getDealerPosition() {
		return Integer.parseInt(dealerPosition.getResultAsString());
	}

	@Override
	public Fold getFoldButtonStatus() {
		return foldButton.getResultAsString().equals("active") ? Fold.ACTIVE : Fold.INACTIVE;
	}

	@Override
	public int getTotalChipsOf(int playerIndex) {
		String chipsOnHand = chips.getResultAsString().split(",")[playerIndex];
		String chipsOnTable = tableChips.getResultAsString().split(",")[playerIndex];
		return Integer.parseInt(chipsOnHand) + Integer.parseInt(chipsOnTable);
	}

	@Override
	public int getChipsOnTableOf(int playerIndex) {
		String chipsOnTable = tableChips.getResultAsString().split(",")[playerIndex];
		return Integer.parseInt(chipsOnTable);
	}

	@Override
	public Border getBorderOf(int playerIndex) {
		String label = borders.getResultAsString().split(",")[playerIndex-1];
		return new PokerBorder(label);
	}

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append("######################################\n");
        sb.append("#"); sb.append("\tHuman\n");
        sb.append("#"); sb.append("\t\t1st card:\t"+getFirstCard()+"\n");
        sb.append("#"); sb.append("\t\t2nd card:\t"+getSecondCard()+"\n");
        sb.append("#"); sb.append("\t\tdealer pos.:\t"+getDealerPosition()+"\n");
        sb.append("#"); sb.append("\t\tfold button:\t"+getFoldButtonStatus()+"\n");
        for (int i = 0; i < 6; i++) {
            if(i != 0){
                sb.append("#"); sb.append("\tplayer #"+i+"\n");
            }
            sb.append("#"); sb.append("\t\ttotal chips:\t"+getTotalChipsOf(i)+"\n");
            sb.append("#"); sb.append("\t\ttable chips:\t"+getChipsOnTableOf(i)+"\n");
            if(i != 0) {
                sb.append("#"); sb.append("\t\tborder:\t\t"+getBorderOf(i)+"\n");
            }
        }
        sb.append("######################################");
        return sb.toString();
    }

    @Override
    public String smallReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Human\n");
        sb.append("\t1st card:\t"+getFirstCard()+"\n");
        sb.append("\t2nd card:\t"+getSecondCard()+"\n");
        sb.append("\tdealer pos.:\t"+getDealerPosition()+"\n");
        sb.append("\tfold button:\t"+getFoldButtonStatus()+"\n");
        for (int i = 0; i < 6; i++) {
            if(i != 0){
                sb.append("Player #"+i+"\n");
            }
            sb.append("\ttotal chips:\t"+getTotalChipsOf(i)+"\n");
            sb.append("\ttable chips:\t"+getChipsOnTableOf(i)+"\n");
            if(i != 0) {
                sb.append("\tborder:\t\t"+getBorderOf(i)+"\n");
            }
        }
        return sb.toString();
    }
	
	@Override
	public String toString() {
		return report();
	}

}
