package commands;

import content.Movie;
import exceptions.InvalidArgumentException;
import util.CollectionManager;

/**
 * Command remove_greater class
 */
public class RemoveGreater implements Commandable {
    CollectionManager collection;
    final public static String description = "удалить из коллекции все элементы, превышающие заданный";

    /**
     * Constructor of remove_greater command
     *
     * @param collection CollectionManager instance
     */
    public RemoveGreater(CollectionManager collection) {
        this.collection = collection;
    }


    @Override
    public String run(String arg, Movie obj) throws InvalidArgumentException {
        return "Удалено элементов: " + collection.removeGreater(obj);
    }

    public String getDescription() {
        return description;
    }
}
