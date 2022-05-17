package commands;

import content.Movie;
import util.CollectionManager;

/**
 * Command info class
 */
public class Info implements Commandable {
    CollectionManager collection;
    final public static String description = "вывести в стандартный поток вывода информацию о коллекции";

    /**
     * Constructor of info command
     *
     * @param collection CollectionManager instance
     */
    public Info(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public String run(String arg, Movie obj) {
        return collection.getInfo();
    }

    public String getDescription() {
        return description;
    }
}
