package commands;

import content.Movie;
import util.CollectionManager;

/**
 * Command exit class
 */
public class Exit implements Commandable {
    CollectionManager collection;
    final public static String description = "завершить программу";

    public Exit(CollectionManager collection) {
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
