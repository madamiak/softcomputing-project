package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api;

import org.neuroph.util.TransferFunctionType;

import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation.TableElement;

/**
 * Interface to teaching given Neural Network.
 * 
 * @author Mateusz Adamiak
 * 
 */
public interface Teacher {

	/**
	 * Starts learning process of neural network.
	 */
	void teach(TransferFunctionType type, TableElement tableElement,
			boolean blackAndWhite, boolean grayed, double scalingRatio);

	void recognize(String string, TableElement figure, String string2, boolean blackAndWhite, boolean grayed,
			double scalingRatio);

}
