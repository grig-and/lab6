package commands;

import content.Movie;
import exceptions.InvalidArgumentException;
import util.CollectionManager;

/**
 * Command replace_if_greater class
 */
public class ReplaceIfGreater implements Commandable {
    CollectionManager collection;

    final public static String description = "заменить значение по ключу, если новое значение больше старого";

    /**
     * Constructor of replace_if_greater command
     *
     * @param collection CollectionManager instance
     */
    public ReplaceIfGreater(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public String run(String arg, Movie obj) throws InvalidArgumentException {

        if (arg == null){
            throw new InvalidArgumentException("Эта команда требует аргумент: ключ элемента коллекции");
        }

        if (!collection.contains(arg)) {
            throw new InvalidArgumentException("Элемент с таким ключом не существует");
        }

        return collection.replaceIfGreater(arg, obj);
    }

    public String getDescription() {
        return description;
    }
}
