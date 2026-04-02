package uo.mp.lab09;

import uo.mp.lab09.newsstand.service.NewsstandService;
import uo.mp.lab09.newsstand.ui.UserInterface;

public class Main {

	private UserInterface userInterface;

	public static void main(String[] args) {
		new Main().config().run();
	}

	private Main config() {
		NewsstandService service = new NewsstandService();
		userInterface = new UserInterface( service );
		return this;
	}

	private void run() {
		userInterface.start();
	}

}
