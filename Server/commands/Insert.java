package commands;

import content.Movie;
import exceptions.InvalidArgumentException;
import util.CollectionManager;

/**
 * Command insert class
 */
public class Insert implements Commandable {
    CollectionManager collection;
    final public static String description = "добавить новый элемент с заданным ключом";

    /**
     * Constructor of insert command
     *
     * @param collection CollectionManager instance
     */
    public Insert(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public String run(String arg, Movie obj) throws InvalidArgumentException {
        System.out.println("insert" + arg + obj);
        if (arg == null) {
            throw new InvalidArgumentException("Эта команда требует аргумент: ключ элемента коллекции");
        }
        if (collection.contains(arg)) {
            throw new InvalidArgumentException("Элемент с таким ключом уже существует");
        }
        if (obj == null) {
            System.out.println("obj == null");
            obj = Movie.prompt();
            System.out.println(obj);
        }
        collection.insert(arg, obj);
        return "Фильм успешно добавлен";
    }

    public String getDescription() {
        return description;
    }
}
