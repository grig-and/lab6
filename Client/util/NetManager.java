package util;

import exceptions.NetException;

import java.io.*;
import java.net.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;

public class NetManager {
    private int port;
    InetAddress host;
    public Socket sock;
    public OutputStream os;
    public InputStream is;


    private static NetManager net;

    public NetManager(InetAddress host, int port) {
        this.host = host;
        this.port = port;
        this.net = this;
    }

    public static NetManager get() {
        return net;
    }

    public boolean connect() {
        try {
            sock = new Socket(host, port);
            os = sock.getOutputStream();
            is = sock.getInputStream();
            System.out.println("\u001B[32m" + "Connected" + "\u001B[0m");
            return true;
        } catch (ConnectException e) {
//            System.out.println("\u001B[31m" + "Сервер не отвечает" + "\u001B[0m");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("\u001B[31m" + "Неверно указан порт" + "\u001B[0m");
            System.exit(1);
            return false;
        } catch (IOException | NullPointerException e) {
//            e.printStackTrace();
//            System.out.println("\u001B[31m" + "Сервер наелся запросов и спит" + "\u001B[0m");
            return false;
//            System.exit(1);
        }
    }

    public void send(Request req) throws NetException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(req);
        try {
            os.write(baos.toByteArray());
        } catch (SocketException e) {
            throw new NetException("Ошибка подключения: " + e.getLocalizedMessage());
        } catch (IOException e) {
            throw new NetException("Ошибка IO: " + e.getLocalizedMessage());
        }
    }

    public Response read() throws ClassNotFoundException {
        try {
            ByteBuffer buf = ByteBuffer.allocate(1024 * 128);
            is.read(buf.array());
            ByteArrayInputStream bais = new ByteArrayInputStream(buf.array());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Response) ois.readObject();
        } catch (java.io.IOException e) {
            System.out.println("Ошибка чтения");
        }
        return null;
    }


    public void close() throws IOException {
        os.close();
        is.close();
        sock.close();
    }

    public String exchange(Request res) throws NetException, IOException, ClassNotFoundException {
        this.send(res);
        String msg = ((Response) this.read()).getMsg();
        return msg;
    }
}
