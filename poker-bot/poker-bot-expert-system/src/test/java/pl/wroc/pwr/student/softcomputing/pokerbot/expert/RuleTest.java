package pl.wroc.pwr.student.softcomputing.pokerbot.expert;

import org.junit.Test;

public class RuleTest {

	private Rule r;
	private Condition c1;
	private Condition c2;
	private Action carGo;
	private Action carStop;
	private Action pedestrianGo;
	private Action pedestrianStop;
	private Light light;

	@Test
	public void test() {
		r = new Rule();
		light = Light.RED;
		c1 = new Condition(light == Light.GREEN);
		c2 = new Condition(light == Light.RED);
		carGo = new GoAction("car");
		carStop = new StopAction("car");
		pedestrianGo = new GoAction("pedestrian");
		pedestrianStop = new StopAction("pedestrian");
		r.when(c1).then(carGo).then(pedestrianStop);
		r.when(c2).then(carStop).then(pedestrianGo);
	}

	public class Rule {
		private boolean state;

		public Rule when(Condition fact) {
			state = fact.isTrue();
			return this;
		}

		public Rule then(Action a) {
			if(state)
				a.onFired();
			return this;
		}

	}

	public class Condition {
		private boolean b;

		public Condition(boolean b) {
			this.b = b;
		}

		public boolean isTrue() {
			return b;
		}

	}

	public interface Action {
		void onFired();
	}

	public class GoAction implements Action {

		private String string;

		public GoAction(String string) {
			this.string = string;
		}

		public void onFired() {
			System.out.println(string + ": Go");
		}

	}

	public class StopAction implements Action {

		private String string;

		public StopAction(String string) {
			this.string = string;
		}

		public void onFired() {
			System.out.println(string + ": Stop");
		}

	}

	public enum Light {
		RED, GREEN
	}

}
