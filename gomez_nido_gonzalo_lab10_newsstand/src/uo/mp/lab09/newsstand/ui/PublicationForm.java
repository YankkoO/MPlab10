package uo.mp.lab09.newsstand.ui;

import uo.mp.lab09.newsstand.domain.Magazine;
import uo.mp.lab09.newsstand.domain.Magazine.Frequency;
import uo.mp.lab09.newsstand.domain.Newspaper;
import uo.mp.lab09.newsstand.domain.Publication;
import uo.mp.util.console.Console;

/**
 * Asks the user all the data for a new publication.
 * 
 * @version 2026
 */
public class PublicationForm {

    /**
     * Prompts the user to enter details for a new publication.
     * 
     * @return A newly created Publication object, which will be an instance of
     *         any subclass of Publication.
     */
    public Publication askForPublication() {
        String type = Console.readString("Enter type of publication (n | m):");
        type = type.toLowerCase();
        switch (type) {
        case "n":
            return askForNewsPaper();
        case "m":
            return askForMagazine();
        default:
            return null;
        }

    }

    private Publication askForMagazine() {
        String name = Console.readString("Enter name: ");
        int stock = Console.readInt("Enter stock: ");
        int sales = Console.readInt("Enter sales: ");
        Frequency f = readFrequency();

        // Frequency.valueOf(frequency.toUpperCase()));
        return new Magazine(name, stock, sales, f);
    }

    private Frequency readFrequency() {
        String frequency = Console.readString("Enter frequency: ");
        while (!Frequency.getNames().contains(frequency.toLowerCase())) {
            frequency = Console.readString("Enter frequency: ");
        }
        return Frequency.valueOf(frequency.toUpperCase());
    }

    private Publication askForNewsPaper() {
        String name = Console.readString("Enter name: ");
        int stock = Console.readInt("Enter stock: ");
        int sales = Console.readInt("Enter sales: ");

        return new Newspaper(name, stock, sales);
    }

}
