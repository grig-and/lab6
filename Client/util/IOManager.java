package util;

import java.io.BufferedReader;
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

    public static boolean isFileMode() {
        return isFileMode;
    }

    public static void setIsFileMode(boolean isFileMode) {
        IOManager.isFileMode = isFileMode;
    }

    private static boolean isFileMode = false;
    private static BufferedReader file;

    public static void setFileReader(BufferedReader reader) {
        file = reader;
    }


    public static String nextLine() {
        try {
            if (isFileMode) {
                return file.readLine();
            } else {
                Scanner scanner = new Scanner(System.in);
                return scanner.nextLine();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Нажат Ctrl+D - выхожу из программы");
            System.exit(0);

        } catch (IOException e) {
        }
        return null;
    }

    /**
     * @return array of user entered arguments
     */
    public static String[] promptArgs() {
        String line = "";
        line = nextLine();
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
        if (!isFileMode) System.out.print(msg);
        String line = "";
        line = nextLine();
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
