package commands;

import content.Movie;
import exceptions.InvalidArgumentException;
import util.CollectionManager;

/**
 * Command remove_greater_key class
 */
public class RemoveGreaterKey implements Commandable {
    CollectionManager collection;
    final public static String description = "удалить из коллекции все элементы, ключ которых превышает заданный";

    /**
     * Constructor of remove_greater_key command
     *
     * @param collection CollectionManager instance
     */
    public RemoveGreaterKey(CollectionManager collection) {
        this.collection = collection;
    }


    @Override
    public String run(String arg, Movie obj) throws InvalidArgumentException {
        if (arg == null){
            throw new InvalidArgumentException("Эта команда требует аргумент: ключ элемента коллекции");
        }

        return "Удалено элементов: " + collection.removeGreaterKey(arg);
    }

    public String getDescription() {
        return description;
    }
}
