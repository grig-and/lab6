import java.io.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class
 */
public class Main {
    private static Log log = new Log();

    public static void main(String[] args) throws IOException {
        Telegram tg = new Telegram();
        tg.sendMessage("Server started");
        tg.sendMessage("Этот бот логгирует все подключения и команды. Также может выполнять команды, не требующие ввода объекта");

        NetManager net = null;
        try {
            net = new NetManager(Integer.parseInt(args[1]));
//            net = new NetManager(33508);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            log.error("Неверный формат порта");
            tg.sendMessage("port_error");
            System.exit(1);
        }
        net.init();
        FileManager fileManager;
        CommandManager commandManager = null;
        CollectionManager collectionManager = null;

        try {
            fileManager = new FileManager(args[0]);
            tg.sendMessage(args[0]);
//            fileManager = new FileManager("C:/Users/Andrey/IdeaProjects/lab6_server/src/main/java/1.csv");
            collectionManager = new CollectionManager(fileManager);
            commandManager = new CommandManager(collectionManager);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Не передан путь");
            tg.sendMessage("path_error");
            System.exit(1);
        }

        Request req;
        while (true) {
            if (System.in.available() > 0) {
                if (new Scanner(System.in).nextLine().trim().equals("save")) {
                    collectionManager.save();
                }
            }

            String[] cmd = tg.getCMD();
            if (cmd != null) {
                tg.sendMessage(commandManager.run(cmd));
            }

            net.getSelector().select(500);
            Set keys = net.getSelector().selectedKeys();
            Iterator i = keys.iterator();
            while (i.hasNext()) {
                SelectionKey selectionKey = (SelectionKey) i.next();
                i.remove();
                Accepter acc = new Accepter(net);
                acc.accept(selectionKey);
                Receiver receiver = new Receiver(net);
                if (selectionKey.isReadable()) {
                    SocketChannel sc = (SocketChannel) selectionKey.channel();
                    receiver.init(sc);
                    try {
                        if (receiver.isAvailable()) {
                            req = receiver.read();
                            if (req != null) {
                                String res = commandManager.runPrepared(req);
                                Response resp = new Response(res);
                                Sender.send(resp, sc);
                                tg.sendMessage(resp.getMsg());
                            }
                        }
                    } catch (IOException e) {
                        log.info("Client " + sc.getRemoteAddress() + " disconnected");
                        tg.sendMessage("Client " + sc.getRemoteAddress() + " disconnected");
                        collectionManager.save();
                        selectionKey.cancel();
                    }
                }
            }
        }
    }
}


