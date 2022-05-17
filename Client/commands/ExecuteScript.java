package commands;


import exceptions.InvalidArgumentException;
import util.Request;

/**
 * Command execute_script class
 */
public class ExecuteScript extends Commandable {
    final public static String name = "execute_script";
    final public static String description = "считать и исполнить скрипт из указанного файла";

    @Override
    public Request run(String arg) throws InvalidArgumentException {
        if (arg == null) throw new InvalidArgumentException("Необходим аргумент - путь");
        return new Request(name, arg);
    }

    public String getDescription() {
        return description;
    }
}
