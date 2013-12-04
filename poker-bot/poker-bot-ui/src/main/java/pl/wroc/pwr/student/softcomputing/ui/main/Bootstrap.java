package pl.wroc.pwr.student.softcomputing.ui.main;

import java.awt.EventQueue;

public class Bootstrap {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bootstrap application = new Bootstrap();
					application.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected void start() {
		MainDisplay view = new MainView();
		MainController mainController = new MainController(view);
		view.setMainController(mainController);
		view.setFrameVisible(true);
	}

}
