package pl.wroc.pwr.student.softcomputing.teacher.recognition.figures;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.Result;

public class FigureResult implements Result {

	private final String result;

	public FigureResult(String result) {
		this.result = result;
	}

	@Override
	public String getResultAsString() {
		return result;
	}

}
