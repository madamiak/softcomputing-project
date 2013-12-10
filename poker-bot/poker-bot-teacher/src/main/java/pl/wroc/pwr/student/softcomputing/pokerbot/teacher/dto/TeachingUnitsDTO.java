package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeachingUnitsDTO {

	private final List<TeachingUnitDTO> teachingUnits = new ArrayList<>();

	public void add(TeachingUnitDTO teachingUnit) {
		teachingUnits.add(teachingUnit);
	}

	public List<TeachingUnitDTO> list() {
		return Collections.unmodifiableList(teachingUnits);
	}

}
