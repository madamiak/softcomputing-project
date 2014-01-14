package pl.wroc.pwr.student.softcomputing.teacher.api.model;

public interface Table {

	void setFigures(Result figures);
	
	void setSuits(Result suits);

	void setDealerPosition(Result dealerPosition);

	String report();

}
