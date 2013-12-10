package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api;

import org.neuroph.core.learning.DataSet;

import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.dto.TeachingUnitsDTO;

public interface InputBuilder {
	public DataSet prepareInput(TeachingUnitsDTO toLearn);
}