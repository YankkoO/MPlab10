package uo.mp.lab09.newsstand.ui;

import uo.mp.util.console.Console;

/**
 * Shows the user a menu of options. Each option will be identified by a
 * sequential number.
 * 
 * @version 2026
 */
public class Menu {
    public static final int EXIT = 0;
	private static final int MAX_OPT = 9;
	private static final int MIN_OPT = 0;

	private String[] options = { 
			"Load information from a file", 
			"Show current publications", 
			"Add a new publication",
			"Remove a publication", 
			"Create orders", 
			"Show current orders", 
			"", 
			"SaveOrders to file",
			"Export publications to zip", 
			"Import publications from zip" 
		};


    public void show() {
        int i = 1;
        for (String line : options) {
            if ("".equals(line)) {
                Console.println("\t----------------------");
                continue;
            }
            Console.print(String.format("\t%2d- %s\n", i++, line));
        }
        Console.println("\t0- Exit");
    }

	public int readOption() {
        int option = Console.readInt("Enter option");
        while ( outOfRange(option) ) {
			Console.println("Oops! The option must be between 1 and 8. Try again.");
            option = Console.readInt("Enter option");
        }
        return option;
    }

    private boolean outOfRange(int option) {
        return option < MIN_OPT || option > MAX_OPT;
    }

}
