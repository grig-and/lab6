package commands;

import content.Movie;
import util.CollectionManager;

/**
 * Command show class
 */
public class Show implements Commandable {
    CollectionManager collection;
    final public static String description = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";

    /**
     * Constructor of show command
     *
     * @param collection CollectionManager instance
     */
    public Show(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public String run(String arg, Movie obj) {
        return collection.toString();
    }

    public String getDescription() {
        return description;
    }
}
