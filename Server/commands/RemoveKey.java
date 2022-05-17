package commands;

import content.Movie;
import exceptions.InvalidArgumentException;
import util.CollectionManager;

/**
 * Command remove_key class
 */
public class RemoveKey implements Commandable {
    CollectionManager collection;
    final public static String description = "удалить элемент из коллекции по его ключу";

    /**
     * Constructor of remove_key command
     *
     * @param collection CollectionManager instance
     */
    public RemoveKey(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public String run(String arg, Movie obj) throws InvalidArgumentException {
        if (arg == null){
            throw new InvalidArgumentException("Эта команда требует аргумент: ключ элемента коллекции");
        }
        if (!collection.contains(arg)) {
            throw new InvalidArgumentException("Элемента с таким ключом не существует");
        }
        collection.removeKey(arg);
        return "Успешно удалено";
    }

    public String getDescription() {
        return description;
    }
}
