package util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Receiver {
    Telegram tg = new Telegram();
    private NetManager net;
    private ByteBuffer buf;
    private SocketChannel sc;

    public Receiver(NetManager net) {
        this.net = net;
        buf = ByteBuffer.allocate(1024 * 128);
    }

    public void init(SocketChannel sc) {
        buf.clear();
        this.sc = sc;
    }

    public boolean isAvailable() throws IOException {
        return sc.read(buf) > 0;
    }

    public Request read() {
        Request req = net.readBuf(buf);
        tg.sendMessage(req.getCommand() + (req.getArg() == null ? "" : (" " + req.getArg())));
        return req;
    }
}

