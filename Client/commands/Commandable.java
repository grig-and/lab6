package commands;

import exceptions.InvalidArgumentException;
import util.Request;

public abstract class Commandable {
    public static String name = "абстрактная";
    public static String description = "абстрактная команда";

    public abstract Request run(String arg) throws InvalidArgumentException;

    String getDescription() {
        return description;
    }
    String getName() {
        return name;
    }
}
