package commands;

import content.Movie;
import util.CollectionManager;

/**
 * Command save class
 */
public class Save implements Commandable {
    CollectionManager collection;
    final public static String description = "сохранить коллекцию в файл";

    /**
     * Constructor of save command
     *
     * @param collection CollectionManager instance
     */
    public Save(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public String run(String arg, Movie obj) {
        collection.save();
        System.out.println("Сохранено");
        return null;
    }

    public String getDescription() {
        return description;
    }
}
