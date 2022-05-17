package commands;

import content.Movie;
import exceptions.InvalidArgumentException;

/**
 * Interface for commands
 */
public interface Commandable {
    /**
     * @param arg argument of command
     * @throws InvalidArgumentException
     */
    String run(String arg, Movie obj) throws InvalidArgumentException;
    /**
     * @return description for Help command
     */
    String getDescription();
}
