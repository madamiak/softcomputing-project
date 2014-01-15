package pl.wroc.pwr.student.softcomputing.teacher.recognition;

import java.util.List;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.Result;

public class ListResult implements Result {

	private final List<?> chips;

	public ListResult(List<?> chips) {
		this.chips = chips;
	}

	@Override
	public String getResultAsString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < chips.size(); i++) {
			sb.append(chips.get(i) + (i!= chips.size()-1 ? "," : "") );
		}
		return sb.toString();
	}

}
