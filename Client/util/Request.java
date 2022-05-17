package util;

import content.Movie;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 8243L;
    private String command;
    private String arg;
    private Movie obj;


    public Request(String command) {
        this.command = command;
    }

    public Request(String command, String arg) {
        this.command = command;
        this.arg = arg;
    }

    public Request(String command, String arg, Movie obj) {
        this.command = command;
        this.arg = arg;
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command='" + command + '\'' +
                ", arg='" + arg + '\'' +
                ", obj=" + obj +
                '}';
    }

    public String getCommand() {
        return command;
    }

    public String getArg() {
        return arg;
    }

    public Movie getObj() {
        return obj;
    }
}
