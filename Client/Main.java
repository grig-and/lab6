import exceptions.NetException;
import util.*;

import java.io.IOException;
import java.net.InetAddress;
import java.util.NoSuchElementException;

/**
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        CommandManager cm = new CommandManager();
        IOManager io = new IOManager();
        NetManager net = null;
        try {
            net = new NetManager(InetAddress.getLoopbackAddress(), Integer.parseInt(args[0]));
//            net = new NetManager(InetAddress.getLoopbackAddress(), 6789);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Неверно указан порт");
            System.exit(1);
        }
        if (!net.connect()) {
            System.out.println("\u001B[31m" + "Сервер не отвечает" + "\u001B[0m");
            while (!net.connect()) {
            }
        }


        while (true) {
            Request res = null;
            try {
                String[] arr = io.promptArgs();

                res = cm.run(arr);
                if (res != null) {
                    exchange(res, net);
                }
                if (arr[0].equals("exit")) {
                    net.close();
                    System.exit(0);
                }
            } catch (NoSuchElementException e) {
                System.out.println("\u001B[31m" + "Нажат Ctrl+D - выхожу из программы" + "\u001B[0m");
                System.exit(0);
            } catch (NetException e) {
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
                resend(res, net);
            } catch (NullPointerException e) {
                System.out.println("\u001B[31mОшибка отправки\u001B[0m");
                resend(res, net);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    private static void resend(Request res, NetManager net) {
        System.out.println("Попытка переподключения");
        while (!net.connect()) {
        }
        try {
            System.out.println("Повторная отправка");
            exchange(res, net);
        } catch (NetException | ClassNotFoundException | IOException ex) {
            System.out.println("\u001B[31m" + "Ошибка при повторной отправке" + "\u001B[0m");
        }
    }

    private static void exchange(Request res, NetManager net) throws NetException, IOException, ClassNotFoundException {
        net.send(res);

        String msg = ((Response) net.read()).getMsg();

        if (msg != null) {
            System.out.println(msg);
        }
    }


}
