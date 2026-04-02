package uo.mp.lab09.newsstand.ui;

import java.io.FileNotFoundException;
import java.util.List;

import uo.mp.lab09.newsstand.domain.Order;
import uo.mp.lab09.newsstand.domain.Publication;
import uo.mp.lab09.newsstand.service.NewsstandException;
import uo.mp.lab09.newsstand.service.NewsstandService;
import uo.mp.lab09.util.log.Logger;
import uo.mp.util.check.ArgumentChecks;
import uo.mp.util.console.Console;

/**
 * Handles user interaction by:
 * - Displaying the menu options
 * - Processing the user's selection
 * - Requesting any necessary input to complete the action
 * - Displaying the result
 * - Showing an explanatory message in case of an error
 * 
 * Note: This is the only class permitted to display information to the user.
 * 
 * @version 2026
 */
public class UserInterface {
    private NewsstandService newsstand;

    public UserInterface(NewsstandService service) {
    	ArgumentChecks.isNotNull(service);
    	this.newsstand = service;
	}

	public void start() {
        Menu menu = new Menu();

        int option = Menu.EXIT;
        do {
            menu.show();
        	option = menu.readOption();
        	try {
				processOption(option);
			} catch (RuntimeException e) {
				handleSystemError ( e );
				return; 	//Stops the execution
        	} catch (Exception e) {
				handleUserError( e );
			} 
        } while (option != Menu.EXIT);
    }

	private void handleSystemError(RuntimeException e) {
		Console.println("A technical error has happened.");
		Logger.log(e);
		
	}

	private void handleUserError(Exception e) {
		Console.println("An error has happened " + e.getMessage());
		Console.println("Please, mind the error and try again");
		
	}

	private void processOption(int option) throws NewsstandException, FileNotFoundException {
    	switch (option) {
	        case Menu.EXIT: return;
	        case 1: loadFile();          break;
	        case 2: showPublications();  break;
	        case 3: addPublication();    break;
	        case 4: removePublication(); break;
	        case 5: createOrders();      break;
	        case 6: showOrders();        break;
	        case 7: saveOrdersToFile();  break;
	        case 8:	exportToZip();       break;
	        case 9:	importFromZip();     break;
        }
    }

    private void loadFile() throws NewsstandException, FileNotFoundException {    	
        String fileName = Console.readString("Enter file name?");
        newsstand.loadFile(fileName);
    }

    private void addPublication() throws NewsstandException {
    	Publication p = new PublicationForm().askForPublication();
        newsstand.addPublication(p);
    }

    private void removePublication() throws NewsstandException {
        String name = Console.readString("Enter publication name?");
        newsstand.removePublication(name);
    }

    private void showPublications() {
        List<Publication> publications = newsstand.getPublications();
        listPublications(publications);
    }

    private void createOrders() {
        newsstand.createOrders();
    }

    private void listPublications(List<Publication> publications) {
        Console.print("\nList of publications\n");
        Console.print("------------------\n");
        for (Publication p : publications) {
            Console.println( p.toString() );
        }
        Console.println("------------------");
    }

    private void showOrders() {
        List<Order> orders = newsstand.getOrders();
        listOrders(orders);
    }

    private void listOrders(List<Order> orders) {
        Console.print("\nList of orders\n");
        Console.print("------------------\n");
        for (Order o : orders) {
            Console.println( o.toString() );
        }
        Console.println("------------------");
    }

    private void saveOrdersToFile() {
        String fileName = Console.readString("Enter output file name?");
        newsstand.saveOrdersToFile(fileName);
    }

    private void importFromZip() throws NewsstandException, FileNotFoundException {
        String fileName = Console.readString("Enter input ZIP file name?");
        newsstand.importPublicationsFromZipFile(fileName);
    }

    private void exportToZip() {
        String fileName = Console.readString("Output file name?");
        newsstand.exportPublicationsToZipFile(fileName);
    }

}
