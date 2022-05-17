package commands;

import content.Movie;
import util.CollectionManager;

/**
 * Command print_ascending class
 */
public class PrintAscending implements Commandable {
    CollectionManager collection;
    final public static String description = "вывести элементы коллекции в порядке возрастания";

    /**
     * Constructor of print_ascending command
     *
     * @param collection CollectionManager instance
     */
    public PrintAscending(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public String run(String arg, Movie obj) {
        return collection.getAscending();
    }

    public String getDescription() {
        return description;
    }
}
