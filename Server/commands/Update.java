package commands;

import content.Movie;
import exceptions.InvalidArgumentException;
import util.CollectionManager;

/**
 * Command update class
 */
public class Update implements Commandable {
    CollectionManager collection;

    final public static String description = "обновить значение элемента коллекции, id которого равен заданному";

    /**
     * Constructor of update command
     *
     * @param collection CollectionManager instance
     */
    public Update(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public String run(String arg, Movie obj) throws InvalidArgumentException {
        long id;
        try {
            id = Long.parseLong(arg);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Эта команда требует аргумент: id");
        }
        if (!collection.containsID(id)) {
            throw new InvalidArgumentException("Элемента с таким id нет");
        }

        collection.update(id, obj);
        return "Фильм успешно отредактирован";
    }

    public String getDescription() {
        return description;
    }
}
