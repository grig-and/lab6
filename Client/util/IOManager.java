package util;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * IO Manager
 */
public class IOManager {
    public static void out(String str) {
        System.out.print(str);
    }

    /**
     * @return array of user entered arguments
     */
    public static String[] promptArgs() {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        line = scanner.nextLine();
        return parseArgs(line);
    }

    /**
     * @param line line for parse
     * @return array of arguments
     */
    public static String[] parseArgs(String line) {
        return line.trim().split(" ");
    }

    /**
     * @param msg message for show
     * @return parameter
     */
    public static String prompt(String msg) {
        System.out.print(msg);
        Scanner scanner = new Scanner(System.in);
        String line = "";
        try {
            line = scanner.nextLine();

        } catch (NoSuchElementException e) {
            System.out.println("Нажат Ctrl+D - выхожу из программы");
            System.exit(0);
            return null;
        }
        return line.trim();
    }

    /**
     * @param enumValues enum values
     * @param msg        message
     * @return Enum
     */
    public static Enum enumPrompt(Enum[] enumValues, String msg) {
        return enumPrompt(enumValues, msg, "");
    }

    /**
     * @param enumValues enum values
     * @param msg        message
     * @param prefix     prefix
     * @return Enum
     */
    public static Enum enumPrompt(Enum[] enumValues, String msg, String prefix) {
        String options = "";
        for (Enum opt : enumValues) {
            options += prefix + " " + (opt.ordinal() + 1) + ". " + opt + "\n";
        }

        Enum en;

        try {
            String str = IOManager.prompt(prefix + msg + ": \n" +
                    options +
                    prefix + "Введите номер варианта: ");
            if (str.isEmpty()) {
                return null;
            }
            en = enumValues[Integer.parseInt(str) - 1];
        } catch (IndexOutOfBoundsException e) {
            out(prefix + "Введите существующий номер варианта");
            en = enumPrompt(enumValues, msg, " ");
        } catch (NumberFormatException e) {
            out(prefix + "Номер варианта - это число");
            en = enumPrompt(enumValues, msg, " ");
        }

        return en;
    }
}
