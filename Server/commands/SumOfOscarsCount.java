package commands;

import content.Movie;
import util.CollectionManager;

/**
 * Command sum_of_oscars_count class
 */
public class SumOfOscarsCount implements Commandable {
    CollectionManager collection;

    final public static String description = "вывести сумму значений поля oscarsCount для всех элементов коллекции";

    /**
     * Constructor of sum_of_oscars_count command
     *
     * @param collection CollectionManager instance
     */
    public SumOfOscarsCount(CollectionManager collection) {
        this.collection = collection;
    }


    @Override
    public String run(String arg, Movie obj) {
        return "Сумма оскаров: " + collection.getSumOscars();
    }

    public String getDescription() {
        return description;
    }
}
