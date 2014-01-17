package pl.wroc.pwr.student.softcomputing.teacher.api.model;

public interface Table {
	
	void setReportName(String reportName);

	void setFigures(Result figures);
	
	void setSuits(Result suits);

	void setDealerPosition(Result dealerPosition);
	
	void setFoldButton(Result foldButton);
	
	void setChips(Result chips);
	
	void setTableChips(Result tableChips);
	
	void setBorders(Result borders);
	
	String getReportName();
	
	Card getFirstCard();
	
	Card getSecondCard();
	
	int getDealerPosition();
	
	Fold getFoldButtonStatus();
	
	int getTotalChipsOf(int playerIndex);
	
	int getChipsOnTableOf(int playerIndex);
	
	Border getBorderOf(int playerIndex);

    String report();

    String smallReport();

}
