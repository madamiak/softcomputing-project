package pl.wroc.pwr.student.softcomputing.teacher.recognition;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.Result;

public class StringResult implements Result {

	private final String result;

	public StringResult(String result) {
		this.result = result;
	}

	@Override
	public String getResultAsString() {
		return result;
	}

}
