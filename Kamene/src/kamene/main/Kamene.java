package kamene.main;

import kamene.consoleui.ConsoleUI;
import kamene.core.Field;

public class Kamene {

	private long startTime;
	private static Kamene instance;

	public Kamene() {
		instance = this;

		ConsoleUI start = new ConsoleUI();

		Field field = new Field(4, 4);

		startTime = System.currentTimeMillis();

		start.newGameStarted(field);
	}

	public int getTime() {
		return (int) (System.currentTimeMillis() - startTime) / 1000;
	}

	public static Kamene getInstance() {
		return instance;
	}

	public static void main(String[] args) {
		new Kamene();

	}

}
