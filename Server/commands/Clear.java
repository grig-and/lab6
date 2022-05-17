package commands;
import content.Movie;
import util.CollectionManager;

/**
 * Command clear class
 */
public class Clear implements Commandable {
    CollectionManager collection;
    final public static String description = "очистить коллекцию";

    /**
     * Constructor of clear command
     * @param collection CollectionManager instance
     */
    public Clear(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public String run(String arg, Movie obj) {
        collection.clear();
        return "Успешно очищено";
    }

    public String getDescription() {
        return description;
    }
}
